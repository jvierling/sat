package at.skagen.apps.sat.parser.formula.node;

import java.util.HashSet;
import java.util.Set;

import at.skagen.apps.sat.parser.formula.BooleanEvaluator;
import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.StepEvaluator;

public class VariableNode extends NullaryNode {

	private String identifier;
	
	public VariableNode(String identifier) {
		super(identifier);
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public boolean evaluate(BooleanEvaluator evaluator) throws EvaluatorException {
		return evaluator.evaluate(this);
	}

	public String evaluate(StepEvaluator evaluator, int limit) throws EvaluatorException {
		return evaluator.evaluate(this, limit);
	}

	@Override
	public Set<String> registerSymbols() {
		
		HashSet<String> identifiers = new HashSet<String>();
		identifiers.add(identifier);
		
		return identifiers;
	}
}
