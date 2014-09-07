package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.VariableNode;

public class VariableFormula extends AtomicFormula {

	private VariableNode variable;
	
	private boolean value;
	
	public VariableFormula(VariableNode variable, boolean value) {
		this.variable = variable;
		this.value = value;
	}

	public FormulaNode getFormula() {
		return variable;
	}

	public boolean getValue() {
		return value;
	}

	public boolean isAtomic() {
		return true;
	}

	public boolean isContradictory() {
		return false;
	}

	public boolean isContradictory(TableauFormula formula) {
		return formula.getFormula().equals(this.variable)
				&& formula.getValue() != this.value;
	}
}
