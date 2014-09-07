package at.skagen.apps.sat.parser.formula.node;

import java.util.HashSet;
import java.util.Set;

import at.skagen.apps.sat.parser.formula.BooleanEvaluator;
import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.StepEvaluator;

public class ConstantNode extends NullaryNode {

	private boolean value;
	
	public ConstantNode(boolean value) {
		super(value ? "1" : "0");
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	@Override
	public Set<String> registerSymbols() { return new HashSet<String>(); }

	public boolean evaluate(BooleanEvaluator evaluator)
			throws EvaluatorException {
		return evaluator.evaluate(this);
	}

	public String evaluate(StepEvaluator evaluator, int limit) throws EvaluatorException {
		return evaluator.evaluate(this, limit);
	}
	
}
