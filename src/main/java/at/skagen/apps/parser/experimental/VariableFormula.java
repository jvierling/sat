package at.skagen.apps.parser.experimental;

import java.util.Set;

import at.skagen.apps.sat.parser.experimental.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.experimental.formula.node.VariableNode;

public class VariableFormula extends UnexpandableFormula {

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

	public boolean isClosed(Set<TableauFormula> formulas) {
		for (TableauFormula formula : formulas) {
			if (variable.equals(formula.getFormula()) && this.value != formula.getValue()) {
				return true;
			}
		}
		
		return false;
	}
}
