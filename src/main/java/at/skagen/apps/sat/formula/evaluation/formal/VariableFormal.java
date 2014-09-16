package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class VariableFormal extends FormalNode {

	public VariableFormal(FormulaNode formula) {
		super(formula);
	}

	@Override
	public String evaluateFormal(Interpretation interpretation, int limit) {
		
		String result = "";
		
		if (limit == 0) {
			result = "I(" + printFormula(this) + ")"; 
		} else {
			this.setEvaluated(true);
			result = evaluateFormula(this, interpretation);
		}
		
		return result;
	}

}
