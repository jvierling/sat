package at.skagen.apps.parser.experimental;

import java.util.Set;

import at.skagen.apps.sat.parser.experimental.formula.node.FormulaNode;

public class ExpandableFormula implements TableauFormula {

	private boolean value;
	
	private FormulaNode formula;
	
	public ExpandableFormula(FormulaNode formula, boolean value) {
		this.formula = formula;
		this.value   = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public FormulaNode getFormula() {
		return formula;
	}
	
	public boolean isClosed(Set<TableauFormula> formulas) {
		for (TableauFormula formula : formulas) {
			if (this.formula.equals(formula.getFormula()) && this.value != formula.getValue()) {
				return true;
			}
		}
		
		return false;
	}

	public boolean isExpandable() {
		return true;
	}
}
