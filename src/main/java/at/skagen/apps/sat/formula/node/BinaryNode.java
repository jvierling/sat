package at.skagen.apps.sat.formula.node;

import java.util.Set;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.FormalEvaluation;

public abstract class BinaryNode extends FormulaNode {

	private FormulaNode left;
	private FormulaNode right;
	
	private String label;
	
	public BinaryNode(String label, FormulaNode left, FormulaNode right) {
		this.label = label;
		this.left = left;
		this.right = right;
	}
	
	public FormulaNode getRightChild() {
		return right;
	}
	
	public FormulaNode getLeftChild() {
		return left;
	}
	
	public String getLabel() {
		return label;
	}
	
	@Override
	public Set<String> registerSymbols() {
		
		Set<String> symbols = left.registerSymbols();
		symbols.addAll(right.registerSymbols());
		
		return symbols;
	}
	
	@Override
	public String toString() {
		return left.toString() + " " + label + " " + right.toString();
	}
	
	public String evaluateFormal(FormalEvaluation evaluator, int limit) throws EvaluatorException {
		return evaluator.evaluateFormal(this, limit);
	}
}
