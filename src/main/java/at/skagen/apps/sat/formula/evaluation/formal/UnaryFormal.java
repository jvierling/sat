package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class UnaryFormal extends FormalNode {

	private FormalNode operand;
	private String operator;
	
	public UnaryFormal(FormulaNode formula, FormalNode operand, String operator) {
		super(formula);
		this.operand  = operand;
		this.operator = operator;
	}

	@Override
	public String evaluateFormal(Interpretation interpretation, int limit) {
		
		String result = "";
		
		if (limit == 0) {
			result = operator + " val(I, " + printFormula(operand) + ")";
		} else if (operand.isEvaluated()) {
			setEvaluated(true);
			result = evaluateFormula(this, interpretation);
		} else {
			result = operator + " " + operand.evaluateFormal(interpretation, limit - 1);
		}
		
		return result;
	}
}
