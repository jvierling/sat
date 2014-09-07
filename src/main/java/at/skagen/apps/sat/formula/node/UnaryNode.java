package at.skagen.apps.sat.formula.node;

import java.util.Set;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.FormalEvaluation;

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
