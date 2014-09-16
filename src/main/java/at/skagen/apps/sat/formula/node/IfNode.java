package at.skagen.apps.sat.formula.node;

public class IfNode extends BinaryNode {

	public IfNode(FormulaNode left, FormulaNode right) {
		super("if", left, right);
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
				&& ((IfNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((IfNode) other).getRightChild().equals(this.getRightChild());
	}
}
