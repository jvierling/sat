package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class BooleanEvaluation extends CompleteInterpretedEvaluation<Boolean> {

	private FormulaNode formula;
	private Interpretation interpretation;
	
	public BooleanEvaluation(FormulaNode formula, Interpretation interpretation) {
		this.formula = formula;
		this.interpretation = interpretation;
	}
	
	@Override
	public Boolean evaluate() throws EvaluatorException {
		
//		doSemanticalAnalysis(interpetations, identifiers);
		
		BooleanEvaluator evaluator = new BooleanEvaluator(interpretation);
		evaluator.dispatchVisit(formula);
		return evaluator.getResult();
	}

}
