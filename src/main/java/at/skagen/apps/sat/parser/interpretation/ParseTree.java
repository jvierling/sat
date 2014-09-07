package at.skagen.apps.sat.parser.interpretation;

public class ParseTree {

	private InterpretationNode root;
	
	public ParseTree(InterpretationNode root) {
		this.root = root;
	}
	
	public InterpretationNode getRoot() {
		return root;
	}
}
