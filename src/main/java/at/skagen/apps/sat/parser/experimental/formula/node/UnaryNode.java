package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.Set;

import at.skagen.apps.parser.experimental.FormalEvaluation;
import at.skagen.apps.sat.parser.formula.EvaluatorException;

public abstract class UnaryNode extends FormulaNode {

	private FormulaNode operand;
	
	private String label;
	
	public UnaryNode(String label, FormulaNode operand) {
		this.label = label;
		this.operand = operand;
	}
	
	public FormulaNode getOperand() {
		return operand;
	}

	@Override
	public Set<String> registerSymbols() {
		return operand.registerSymbols();
	}

	@Override
	public String toString() {
		return label + " (" + operand.toString() + ")";
	}

	public String getLabel() {
		return label;
	}
	
	public String evaluateFormal(FormalEvaluation evaluation, int limit) throws EvaluatorException {
		return evaluation.evaluateFormal(this, limit);
	}
}
