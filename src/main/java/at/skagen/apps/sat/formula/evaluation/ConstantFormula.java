package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.ConstantNode;

public class ConstantFormula extends AtomicFormula {

	private ConstantNode constant;
	
	public ConstantFormula(ConstantNode constant, boolean value) {
		super(constant, value);
		this.constant = constant;
	}

	public boolean isContradictory() {
		return constant.getValue() != getValue();
	}
}
