package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;

public abstract class TableauFormula {
	
	private FormulaNode formula;
	
	private boolean value;
	
	public TableauFormula(FormulaNode formula, boolean value) {
		this.formula = formula;
		this.value   = value;
	}
	
	public FormulaNode getFormula() {
		return formula;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public boolean isContradictory(TableauFormula formula) {
		return formula.getFormula().equals(this.formula)
				&& formula.getValue() != this.value;
	}
	
	public abstract boolean isAtomic();
	
	public abstract boolean isContradictory();
	
	@Override
	public String toString() {
		return (value ? "1" : "0") + " : " + formula;
	}
}
