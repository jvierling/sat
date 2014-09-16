package at.skagen.apps.sat.formula.node;

import java.util.HashSet;
import java.util.Set;

public class VariableNode extends NullaryNode {

	private String identifier;
	
	public VariableNode(String identifier) {
		super(identifier);
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public Set<String> registerSymbols() {
		
		HashSet<String> identifiers = new HashSet<String>();
		identifiers.add(identifier);
		
		return identifiers;
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
				&& ((VariableNode) other).identifier.equals(this.identifier);
	}
}
