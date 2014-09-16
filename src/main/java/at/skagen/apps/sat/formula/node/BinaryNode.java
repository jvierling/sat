package at.skagen.apps.sat.formula.node;

public abstract class BinaryNode implements FormulaNode {

	private FormulaNode left;
	private FormulaNode right;
	
	private String label;
	
	public BinaryNode(String label, FormulaNode left, FormulaNode right) {
		this.label = label;
		this.left = left;
		this.right = right;
	}
	
	public FormulaNode getRightChild() {
		return right;
	}
	
	public FormulaNode getLeftChild() {
		return left;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return left.toString() + " " + label + " " + right.toString();
	}
}
