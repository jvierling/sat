package at.skagen.apps.sat.parser.formula.node;

import at.skagen.apps.sat.parser.formula.BooleanEvaluator;
import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.StepEvaluator;

public class XorNode extends BinaryNode {

	public XorNode(FormulaNode left, FormulaNode right) {
		super("xor", left, right);
	}
	
	public boolean evaluate(BooleanEvaluator evaluator) throws EvaluatorException {
		return evaluator.evaluate(this);
	}

	public String evaluate(StepEvaluator evaluator, int limit) throws EvaluatorException {
		return evaluator.evaluate(this, limit);
	}
}
