package at.skagen.apps.sat.parser.interpretation;

import java.util.LinkedList;
import java.util.List;

public class InterpretationNode {

	private List<VariableInterpretationNode> children = new LinkedList<VariableInterpretationNode>();
	
	public void addAllChildren(List<VariableInterpretationNode> child) {
		children.addAll(child);
	}
	
	public List<VariableInterpretationNode> getChildren() {
		return children;
	}
}
