package at.skagen.apps.sat.parser.interpretation;

public class VariableInterpretationNode {

	private String identifier;
	
	private Symbols value;
	
	public VariableInterpretationNode(String identifer, Symbols value) {
		this.identifier = identifer;
		this.value      = value;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public Symbols getValue() {
		return value;
	}
}
