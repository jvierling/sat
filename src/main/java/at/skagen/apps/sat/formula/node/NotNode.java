package at.skagen.apps.sat.formula.node;

public class NotNode extends UnaryNode {
	
	public NotNode(FormulaNode operand) {
		super("not", operand);
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
				&& ((NotNode) other).getOperand().equals(this.getOperand());
	}
}
