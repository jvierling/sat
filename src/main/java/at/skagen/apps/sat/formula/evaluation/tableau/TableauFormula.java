package at.skagen.apps.sat.formula.evaluation.tableau;

import at.skagen.apps.sat.formula.node.FormulaDecorator;
import at.skagen.apps.sat.formula.node.FormulaNode;

public class TableauFormula extends FormulaDecorator {

	private boolean value;
	
	public TableauFormula(FormulaNode formula, boolean value) {
		super(formula);
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public boolean isAtomic() {
		AtomicMatcher matcher = new AtomicMatcher();
		matcher.dispatchVisit(this);
		return matcher.getResult();
	}
	
	public boolean isContradictory() {
		ContradictionChecker checker = new ContradictionChecker(value);
		checker.dispatchVisit(this);
		return checker.getResult();
	}
	
	public boolean isContradictory(TableauFormula formula) {
		return formula.getFormula().equals(this.getFormula())
				&& formula.getValue() != this.value;
	}
	
	@Override
	public String toString() {
		return (value ? "1" : "0") + " : " + getFormula();
	}
}
