package at.skagen.apps.sat.formula.node;

public class ConstantNode extends NullaryNode {

	private boolean value;
	
	public ConstantNode(boolean value) {
		super(value ? "1" : "0");
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
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
				&& ((ConstantNode) other).value == this.value;
	}
}
