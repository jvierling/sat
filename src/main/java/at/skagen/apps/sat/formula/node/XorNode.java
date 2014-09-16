package at.skagen.apps.sat.formula.node;

public class XorNode extends BinaryNode {

	public XorNode(FormulaNode left, FormulaNode right) {
		super("xor", left, right);
	}

	public <R, P> R accept(Visitor<R, P> visitor, P parameter) {
		return visitor.visit(this, parameter);
	}

	public void accept(Visitor<?, ?> visitor) {
		visitor.visit(this, null);
	}
	
	public boolean equals(Object other) {
		return other != null
				&& other.getClass() == this.getClass()
				&& ((XorNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((XorNode) other).getRightChild().equals(this.getRightChild());
	}
}
