package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;

public interface TableauFormula {
	
	public FormulaNode getFormula();
	
	public boolean getValue();
	
	public boolean isAtomic();
	
	public boolean isContradictory();
	
	public boolean isContradictory(TableauFormula formula);
}
