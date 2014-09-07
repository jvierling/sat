package at.skagen.apps.sat.formula.evaluation;

import java.util.Map;

import at.skagen.apps.sat.formula.node.BinaryNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.UnaryNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.parser.interpretation.InterpretationEvaluator;

public class FormalEvaluation extends CompleteInterpretedEvaluation<String> {

	private ParseTree formula;
	
	private Map<String, Boolean> interpretations;
	
	public FormalEvaluation(String formula, String interpretations) throws EvaluatorException, ParserException {
		this.formula = new FormulaParser().parse(formula);
		this.interpretations = new InterpretationEvaluator().evaluate(interpretations);
		doSemanticalAnalysis(this.interpretations, this.formula.getRoot().registerSymbols());
	}
	
	@Override
	public String evaluate() throws EvaluatorException {
		
		String result = "val(I, " + formula.getRoot().toString() + ")";
		
		for (int limit = 0; !formula.getRoot().isEvaluated(); limit++) {
			result += "\n = " + formula.getRoot().evaluateFormal(this, limit);
		}
		
		return result;
	}

	public String evaluateFormal(BinaryNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		FormulaNode left  = node.getLeftChild();
		FormulaNode right = node.getRightChild();
		
		String operator = node.getLabel().toUpperCase();
		
		if (limit == 0) {
			result = "(val(I, " + left.toString() + ")" + " " + operator + " "
					+ "val(I, " + right.toString() + "))";
		} else if (left.isEvaluated() && right.isEvaluated()) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluateBoolean(this, interpretations));
		} else {
			result = "(" + left.evaluateFormal(this, limit - 1) + " " + operator + " " 
						+ right.evaluateFormal(this, limit - 1) + ")";
		}
		
		return result;
	}
	
	public String evaluateFormal(ConstantNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		if (limit == 0) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluateBoolean(this, interpretations));
		} else {
			result = booleanRepresentation(node.evaluateBoolean(this, interpretations));
		}
		
		return result;
	}
	
	public String evaluateFormal(UnaryNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		FormulaNode operand  = node.getOperand();
		
		String operator = node.getLabel().toUpperCase();
		
		if (limit == 0) {
			result = operator + " val(I, " + operand.toString() + ")";
		} else if (operand.isEvaluated()) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluateBoolean(this, interpretations));
		} else {
			result = operator + " " + operand.evaluateFormal(this, limit - 1);
		}
		
		return result;
	}

	public String evaluateFormal(VariableNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		if (limit == 0) {
			result = "I(" + node.getIdentifier() + ")"; 
		} else {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluateBoolean(this, interpretations));
		}
		
		return result;
	}
	
	private String booleanRepresentation(boolean value) {
		return value ? "1" : "0";
	}
}
