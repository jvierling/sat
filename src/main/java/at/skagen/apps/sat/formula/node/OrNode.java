package at.skagen.apps.sat.formula.node;

public class OrNode extends BinaryNode {

	public OrNode(FormulaNode left, FormulaNode right) {
		super("or", left, right);
	}
	
	public void accept(Visitor<?, ?> visitor) {
		visitor.visit(this, null);
	}
	
	public <R, P> R accept(Visitor<R, P> visitor, P parameter) {
		return visitor.visit(this, parameter);
	}
	
	public boolean equals(Object other) {
		return other != null
				&& other.getClass() == this.getClass()
				&& ((OrNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((OrNode) other).getRightChild().equals(this.getRightChild());
	}
}
