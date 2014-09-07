package at.skagen.apps.sat.formula.evaluation;

import java.util.Set;

import at.skagen.apps.sat.formula.node.FormulaNode;

public interface TableauFormula {
	
	public FormulaNode getFormula();
	
	public boolean getValue();
	
	public boolean isClosed(Set<TableauFormula> formulas);
	
	public boolean isExpandable();
}
