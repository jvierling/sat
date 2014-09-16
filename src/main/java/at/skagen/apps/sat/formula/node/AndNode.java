package at.skagen.apps.sat.formula.node;

public class AndNode extends BinaryNode {

	public AndNode(FormulaNode left, FormulaNode right) {
		super("and", left, right);
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
				&& ((AndNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((AndNode) other).getRightChild().equals(this.getRightChild());
	}
}
