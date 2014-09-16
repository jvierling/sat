package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class ConstantFormal extends FormalNode {

	public ConstantFormal(FormulaNode formula) {
		super(formula);
	}

	@Override
	public String evaluateFormal(Interpretation interpretation, int limit) {
		
		String result = "";
		
		if (limit == 0) {
			this.setEvaluated(true);
			result = evaluateFormula(this, interpretation);
		} else {
			result = evaluateFormula(this, interpretation);
		}
		
		return result;
	}

}
