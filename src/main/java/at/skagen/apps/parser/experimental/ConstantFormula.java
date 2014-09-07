package at.skagen.apps.parser.experimental;

import java.util.Set;

import at.skagen.apps.sat.parser.experimental.formula.node.ConstantNode;
import at.skagen.apps.sat.parser.experimental.formula.node.FormulaNode;

public class ConstantFormula extends UnexpandableFormula {

	private ConstantNode constant;
	
	private boolean value;
	
	public ConstantFormula(ConstantNode constant, boolean value) {
		this.constant = constant;
		this.value = value;
	}

	public FormulaNode getFormula() {
		return constant;
	}

	public boolean getValue() {
		return value;
	}

	public boolean isClosed(Set<TableauFormula> formulas) {
		return constant.getValue() != value;
	}
}
