package at.skagen.apps.sat.formula.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.util.BooleanEvaluator;
import at.skagen.apps.sat.formula.util.VariableLister;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class CnfSemanticEvaluation implements UninterpretedEvaluation<FormulaNode> {

	private FormulaNode formula;
	
	private List<String> variables;
	
	public CnfSemanticEvaluation(FormulaNode formula) {
		this.formula = formula;
		VariableLister lister = new VariableLister();
		lister.dispatchVisit(formula);
		variables = new ArrayList<String>(lister.getResult());
		Collections.sort(variables);
	}
	
	public FormulaNode evaluate() throws EvaluatorException {
		if (variables.isEmpty()) {
			BooleanEvaluator evaluator = new BooleanEvaluator(new Interpretation(new HashMap<String, Boolean>()));
			evaluator.dispatchVisit(formula);
			return new ConstantNode(evaluator.getResult());
		}
		
		FormulaNode cnf = computeConjunctiveNormalForm(new HashMap<String, Boolean>(), 0);
		
		return cnf != null ? cnf : new ConstantNode(true);
	}
	
	private FormulaNode computeConjunctiveNormalForm(HashMap<String, Boolean> interpretation, int index) {
	
		FormulaNode result = null;
		
		if (index < variables.size()) {
			interpretation.put(variables.get(index), true);
			FormulaNode left = computeConjunctiveNormalForm(interpretation, index + 1);
			interpretation.put(variables.get(index), false);
			FormulaNode right = computeConjunctiveNormalForm(interpretation, index + 1);
			
			if (left == null || right == null) {
				result = (left == null) ? right : left;
			} else {
				result = new AndNode(left, right);
			}
		} else {
			BooleanEvaluator evaluator = new BooleanEvaluator(new Interpretation(interpretation));
			evaluator.dispatchVisit(formula);
			if (!evaluator.getResult()) {
				result = buildDisjunct(interpretation, 0);
			}
		}
		
		return result;
	}
	
	private FormulaNode buildDisjunct(Map<String, Boolean> interpretation, int index) {
		
		FormulaNode left = new VariableNode(variables.get(index));
		
		if (interpretation.get(variables.get(index))) {
			left = new NotNode(left);
		}
		
		if (index == variables.size() - 1) {
			return left;
		}
		
		return new OrNode(left, buildDisjunct(interpretation, index + 1));
	}
}
