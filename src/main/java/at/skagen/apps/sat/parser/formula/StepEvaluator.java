package at.skagen.apps.sat.parser.formula;

import at.skagen.apps.sat.parser.formula.node.BinaryNode;
import at.skagen.apps.sat.parser.formula.node.ConstantNode;
import at.skagen.apps.sat.parser.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.formula.node.UnaryNode;
import at.skagen.apps.sat.parser.formula.node.VariableNode;

public class StepEvaluator extends FormulaEvaluator<String> {
	
	public StepEvaluator(String formula, String interpretation) throws EvaluatorException {
		super(formula, interpretation);
	}

	@Override
	protected String evaluateFormula(ParseTree formula) throws EvaluatorException {
		
		String result = "val(I, " + formula.getRoot().toString() + ")";
		
		for (int limit = 0; !formula.getRoot().isEvaluated(); limit++) {
			result += "\n\t= " + formula.getRoot().evaluate(this, limit);
		}
		
		return result;
	}

	public String evaluate(BinaryNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		FormulaNode left  = node.getLeftChild();
		FormulaNode right = node.getRightChild();
		
		String operator = node.getLabel().toUpperCase();
		
		if (limit == 0) {
			result = "(val(I, " + left.toString() + ")" + " " + operator + " "
					+ "val(I, " + right.toString() + "))";
		} else if (left.isEvaluated() && right.isEvaluated()) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluate(this));
		} else {
			result = "(" + left.evaluate(this, limit - 1) + " " + operator + " " 
						+ right.evaluate(this, limit - 1) + ")";
		}
		
		return result;
	}

	public String evaluate(ConstantNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		if (limit == 0) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluate(this));
		} else {
			result = booleanRepresentation(node.evaluate(this));
		}
		
		return result;
	}

	public String evaluate(UnaryNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		FormulaNode operand  = node.getOperand();
		
		String operator = node.getLabel().toUpperCase();
		
		if (limit == 0) {
			result = operator + " val(I, " + operand.toString() + ")";
		} else if (operand.isEvaluated()) {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluate(this));
		} else {
			result = operator + " " + operand.evaluate(this, limit - 1);
		}
		
		return result;
	}

	public String evaluate(VariableNode node, int limit) throws EvaluatorException {
		
		String result = "";
		
		if (limit == 0) {
			result = "I(" + node.getIdentifier() + ")"; 
		} else {
			node.setEvaluated(true);
			result = booleanRepresentation(node.evaluate(this));
		}
		
		return result;
	}
	
	private String booleanRepresentation(boolean value) {
		return value ? "1" : "0";
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new StepEvaluator("A and B or 0", "I=(A=1,B=0)").evaluate());
		} catch (EvaluatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
