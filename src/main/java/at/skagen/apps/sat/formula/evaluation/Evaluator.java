package at.skagen.apps.sat.formula.evaluation;

import java.util.Map;

import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.IfNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.NandNode;
import at.skagen.apps.sat.formula.node.NorNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.node.XorNode;

public abstract class Evaluator<T> {

	/**
	 * Evaluates this evaluation.
	 * 
	 * @return the evaluation result
	 * @throws EvaluatorException if an error occured during evaluation
	 */
	public abstract T evaluate() throws EvaluatorException;
	
	protected final Boolean evaluateBoolean(FormulaNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		
		return node.evaluateBoolean(this, interpretations);
	}
	
	public final Boolean evaluateBoolean(NorNode node, Map<String, Boolean> interpretations) throws EvaluatorException {
		return !(node.getLeftChild().evaluateBoolean(this, interpretations) 
				|| node.getRightChild().evaluateBoolean(this, interpretations));
	}
	
	public final Boolean evaluateBoolean(NandNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return !(node.getLeftChild().evaluateBoolean(this, interpretations) 
				&& node.getRightChild().evaluateBoolean(this, interpretations));
	}
	
	public final Boolean evaluateBoolean(IffNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		boolean right = node.getRightChild().evaluateBoolean(this, interpretations);
		boolean left  = node.getLeftChild().evaluateBoolean(this, interpretations);
		
		return (left && right) || (!left && !right);
	}
	
	public final Boolean evaluateBoolean(IfNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		boolean right = node.getRightChild().evaluateBoolean(this, interpretations);
		boolean left  = node.getLeftChild().evaluateBoolean(this, interpretations);
		
		return !left || right;
	}
	
	public final Boolean evaluateBoolean(OrNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		boolean right = node.getRightChild().evaluateBoolean(this, interpretations);
		boolean left  = node.getLeftChild().evaluateBoolean(this, interpretations);
		
		return left || right;
	}
	
	public final Boolean evaluateBoolean(AndNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		boolean right = node.getRightChild().evaluateBoolean(this, interpretations);
		boolean left  = node.getLeftChild().evaluateBoolean(this, interpretations);
		
		return left && right;
	}
	
	public final Boolean evaluateBoolean(ConstantNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return node.getValue();
	}
	
	public final Boolean evaluateBoolean(NotNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return ! node.getOperand().evaluateBoolean(this, interpretations);
	}
	
	public final Boolean evaluateBoolean(VariableNode node, Map<String, Boolean> interpretations)
			throws EvaluatorException {
		
		return interpretations.get(node.getIdentifier());
	}

	public final Boolean evaluateBoolean(XorNode node, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		boolean right = node.getRightChild().evaluateBoolean(this, interpretations);
		boolean left  = node.getLeftChild().evaluateBoolean(this, interpretations);
		
		return (left && !right) || (!left && right); 
	}
}
