package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.VariableNode;

public class VariableFormula extends AtomicFormula {
	
	public VariableFormula(VariableNode variable, boolean value) {
		super(variable, value);
	}

	@Override
	public boolean isContradictory() {
		return false;
	}
}
