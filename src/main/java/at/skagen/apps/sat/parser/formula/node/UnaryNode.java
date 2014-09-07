package at.skagen.apps.sat.parser.formula.node;

import java.util.Set;

public abstract class UnaryNode extends FormulaNode {

	private FormulaNode operand;
	
	private String label;
	
	public UnaryNode(String label, FormulaNode operand) {
		this.label = label;
		this.operand = operand;
	}
	
	public FormulaNode getOperand() {
		return operand;
	}

	@Override
	public Set<String> registerSymbols() {
		return operand.registerSymbols();
	}

	@Override
	public String toString() {
		return label + " (" + operand.toString() + ")";
	}

	public String getLabel() {
		return label;
	}
}
