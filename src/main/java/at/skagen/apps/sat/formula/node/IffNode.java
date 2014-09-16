package at.skagen.apps.sat.formula.node;

public class IffNode extends BinaryNode {

	public IffNode(FormulaNode left, FormulaNode right) {
		super("iff", left, right);
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
				&& ((IffNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((IffNode) other).getRightChild().equals(this.getRightChild());
	}
}
