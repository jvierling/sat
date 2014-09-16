package at.skagen.apps.sat.formula.node;

public abstract class UnaryNode implements FormulaNode {

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
	public String toString() {
		return label + " (" + operand.toString() + ")";
	}

	public String getLabel() {
		return label;
	}
}
