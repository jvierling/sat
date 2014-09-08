package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;

public abstract class AtomicFormula extends TableauFormula {

	public AtomicFormula(FormulaNode formula, boolean value) {
		super(formula, value);
	}

	public boolean isAtomic() {
		return true;
	}
}
