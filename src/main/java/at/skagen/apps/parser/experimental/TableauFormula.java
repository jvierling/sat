package at.skagen.apps.parser.experimental;

import java.util.Set;

import at.skagen.apps.sat.parser.experimental.formula.node.FormulaNode;

public interface TableauFormula {
	
	public FormulaNode getFormula();
	
	public boolean getValue();
	
	public boolean isClosed(Set<TableauFormula> formulas);
	
	public boolean isExpandable();
}
