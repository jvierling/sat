package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.List;
import java.util.Map;

import at.skagen.apps.parser.experimental.Evaluator;
import at.skagen.apps.parser.experimental.TableauEvaluation;
import at.skagen.apps.parser.experimental.TableauFormula;
import at.skagen.apps.parser.experimental.TableauNode;
import at.skagen.apps.sat.parser.formula.EvaluatorException;

public class NandNode extends BinaryNode {

	public NandNode(FormulaNode left, FormulaNode right) {
		super("nand", left, right);
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
