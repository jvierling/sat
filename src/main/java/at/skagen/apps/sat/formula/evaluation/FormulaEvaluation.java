package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class FormulaEvaluation extends CompleteInterpretedEvaluation<Boolean> {

	private FormulaNode formula;
	
	private Interpretation interpretation;
	
	public FormulaEvaluation(FormulaNode formula, Interpretation interpretation) {
		this.formula = formula;
		this.interpretation = interpretation;
//		doSemanticalAnalysis(this.interpretations, this.formula.getRoot().registerSymbols());
	}
	
	/**
	 * Evaluates a formulas boolean value, according to an interpretation
	 */
	@Override
	public Boolean evaluate() throws EvaluatorException {
		BooleanEvaluator evaluator = new BooleanEvaluator(interpretation);
		evaluator.dispatchVisit(formula);
		return evaluator.getResult();
	}
}
