package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class BinaryFormal extends FormalNode {

	private FormalNode left;
	private FormalNode right;
	
	private String operator;
	
	public BinaryFormal(FormulaNode formula, FormalNode left, FormalNode right, String operator) {
		super(formula);
		this.left  = left;
		this.right = right;
		this.operator = operator;
	}

	@Override
	public String evaluateFormal(Interpretation interpretation, int limit) {

		String result = "";
		
		if (limit == 0) {
			result = "(val(I, " + printFormula(left) + ")" + " " + operator + " "
					+ "val(I, " + printFormula(right) + "))";
		} else if (left.isEvaluated() && right.isEvaluated()) {
			setEvaluated(true);
			result = evaluateFormula(this, interpretation);
		} else {
			result = "(" + left.evaluateFormal(interpretation, limit - 1) + " " + operator + " " 
						+ right.evaluateFormal(interpretation, limit - 1) + ")";
		}
		
		return result;
	}

}
