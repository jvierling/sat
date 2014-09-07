package at.skagen.apps.sat.parser.formula;

import java.util.Map;
import java.util.Set;

import at.skagen.apps.sat.parser.formula.node.AndNode;
import at.skagen.apps.sat.parser.formula.node.ConstantNode;
import at.skagen.apps.sat.parser.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.formula.node.IfNode;
import at.skagen.apps.sat.parser.formula.node.IffNode;
import at.skagen.apps.sat.parser.formula.node.NandNode;
import at.skagen.apps.sat.parser.formula.node.NorNode;
import at.skagen.apps.sat.parser.formula.node.NotNode;
import at.skagen.apps.sat.parser.formula.node.OrNode;
import at.skagen.apps.sat.parser.formula.node.VariableNode;
import at.skagen.apps.sat.parser.formula.node.XorNode;
import at.skagen.apps.sat.parser.interpretation.InterpretationEvaluator;

public abstract class FormulaEvaluator<T> {

	private ParseTree formula;
	
	private Map<String, Boolean> interpretations;
	
	public FormulaEvaluator(String formula, String interpretation) throws EvaluatorException {
		
		interpretations = new InterpretationEvaluator().evaluate(interpretation);
		
		try {
			this.formula = new FormulaParser().parse(formula);
			doSemanticalAnalysis(interpretations, this.formula.getRoot().registerSymbols());
		} catch (ParserException e) {
			throw new EvaluatorException(e.getMessage());
		}
	}
	
	protected final void doSemanticalAnalysis(Map<String, Boolean> interpetations, Set<String> identifiers) 
			throws EvaluatorException {
		for (String identifier : identifiers) {
			if (!interpetations.keySet().contains(identifier)) {
				throw new EvaluatorException("undefined variable " + identifier);
			}
		}
		for (String identifier : interpetations.keySet()) {
			if (!identifiers.contains(identifier)) {
				throw new EvaluatorException("unused variable " + identifier);
			}
		}
	}
	
	public T evaluate() throws EvaluatorException {
		return evaluateFormula(formula);
	}
	
	protected abstract T evaluateFormula(ParseTree formula) throws EvaluatorException;
	
	protected final boolean evaluate(FormulaNode root) throws EvaluatorException {
		return root.evaluate(this);
	}
	
	public Boolean evaluate(NorNode node) throws EvaluatorException {
		return !(node.getLeftChild().evaluate(this) || node.getRightChild().evaluate(this));
	}
	
	public Boolean evaluate(NandNode node) throws EvaluatorException {
		return !(node.getLeftChild().evaluate(this) && node.getRightChild().evaluate(this));
	}
	
	public Boolean evaluate(IffNode node) throws EvaluatorException {
		boolean right = node.getRightChild().evaluate(this);
		boolean left  = node.getLeftChild().evaluate(this);
		
		return (left && right) || (!left && !right);
	}
	
	public Boolean evaluate(IfNode node) throws EvaluatorException {
		boolean right = node.getRightChild().evaluate(this);
		boolean left  = node.getLeftChild().evaluate(this);
		
		return !left || right;
	}
	
	public Boolean evaluate(OrNode node) throws EvaluatorException {
		boolean right = node.getRightChild().evaluate(this);
		boolean left  = node.getLeftChild().evaluate(this);
		
		return left || right;
	}
	
	public Boolean evaluate(AndNode node) throws EvaluatorException {
		boolean right = node.getRightChild().evaluate(this);
		boolean left  = node.getLeftChild().evaluate(this);
		
		return left && right;
	}
	
	public Boolean evaluate(ConstantNode node)throws EvaluatorException {
		return node.getValue();
	}
	
	public Boolean evaluate(NotNode node) throws EvaluatorException {
		return ! node.getOperand().evaluate(this);
	}
	
	public Boolean evaluate(VariableNode node)
			throws EvaluatorException {
		
		return interpretations.get(node.getIdentifier());
	}

	public Boolean evaluate(XorNode node) throws EvaluatorException {
		boolean right = node.getRightChild().evaluate(this);
		boolean left  = node.getLeftChild().evaluate(this);
		
		return (left && !right) || (!left && right); 
	}
}
