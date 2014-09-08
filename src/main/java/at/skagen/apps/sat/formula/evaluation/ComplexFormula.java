package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;

public class ComplexFormula extends TableauFormula {
	
	public ComplexFormula(FormulaNode formula, boolean value) {
		super(formula, value);
	}
	
	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public boolean isContradictory() {
		return false;
	}
}
