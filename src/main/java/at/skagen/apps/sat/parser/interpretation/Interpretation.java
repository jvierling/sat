package at.skagen.apps.sat.parser.interpretation;

import java.util.Map;
import java.util.Set;

import at.skagen.apps.sat.formula.node.VariableNode;

public class Interpretation {

	private Map<String, Boolean> interpretation;
	
	public Interpretation(Map<String, Boolean> interpretation) {
		this.interpretation = interpretation;
	}
	
	public Boolean getVariableInterpretation(VariableNode variable) {
		return interpretation.get(variable.getIdentifier());
	}
	
	public Set<String> getDefinedVariables() {
		return interpretation.keySet();
	}
}
