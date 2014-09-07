package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;

public class ConstantFormula extends AtomicFormula {

	private ConstantNode constant;
	
	private boolean value;
	
	public ConstantFormula(ConstantNode constant, boolean value) {
		this.constant = constant;
		this.value = value;
	}

	public FormulaNode getFormula() {
		return constant;
	}

	public boolean getValue() {
		return value;
	}

	public boolean isAtomic() {
		return true;
	}

	public boolean isContradictory() {
		return constant.getValue() != value;
	}

	public boolean isContradictory(TableauFormula formula) {
		return formula.getFormula().equals(this.constant)
				&& formula.getValue() != this.value;
	}
}
