package at.skagen.apps.sat.formula.node;

public class NorNode extends BinaryNode {

	public NorNode(FormulaNode left, FormulaNode right) {
		super("nor", left, right);
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
				&& ((NorNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((NorNode) other).getRightChild().equals(this.getRightChild());
	}
}
