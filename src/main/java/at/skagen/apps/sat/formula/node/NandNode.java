package at.skagen.apps.sat.formula.node;

public class NandNode extends BinaryNode {

	public NandNode(FormulaNode left, FormulaNode right) {
		super("nand", left, right);
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
				&& ((NandNode) other).getLeftChild().equals(this.getLeftChild())
				&& ((NandNode) other).getRightChild().equals(this.getRightChild());
	}
}
