package at.skagen.apps.sat.formula.node;

import java.util.List;
import java.util.Map;

import at.skagen.apps.sat.formula.evaluation.Evaluator;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.TableauEvaluation;
import at.skagen.apps.sat.formula.evaluation.TableauFormula;
import at.skagen.apps.sat.formula.evaluation.TableauNode;

public class IffNode extends BinaryNode {

	public IffNode(FormulaNode left, FormulaNode right) {
		super("iff", left, right);
	}

	public boolean evaluateBoolean(Evaluator<?> evaluator, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return evaluator.evaluateBoolean(this, interpretations);
	}
	
	public List<TableauNode> evaluateTableau(TableauEvaluation evaluation, boolean value) {
		return evaluation.evaluateTableau(this, value);
	}
	
	public TableauFormula toTableauFormula(TableauEvaluation evaluation, boolean value) {
		return evaluation.toTableauFormula(this, value);
	}
}
