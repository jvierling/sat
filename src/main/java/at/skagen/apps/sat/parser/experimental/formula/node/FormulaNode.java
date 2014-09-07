package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.Set;

public abstract class FormulaNode implements BooleanEvaluatorNode, FormalEvaluatorNode, TableauEvaluatorNode {
	
	private boolean isEvaluated;
	
	public abstract Set<String> registerSymbols();
	
	public void setEvaluated(boolean isEvaluated) {
		this.isEvaluated = isEvaluated;
	}
	
	public boolean isEvaluated() {
		return isEvaluated;
	}
}
