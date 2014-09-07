package at.skagen.apps.parser.experimental;

import java.util.Map;

import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.ParserException;
import at.skagen.apps.sat.parser.interpretation.InterpretationEvaluator;

public class FormulaEvaluation extends CompleteInterpretedEvaluation<Boolean> {

	private ParseTree formula;
	
	private Map<String, Boolean> interpretations;
	
	public FormulaEvaluation(String formula, String interpretations) throws EvaluatorException, ParserException {
		this.formula = new FormulaParser().parse(formula);
		this.interpretations = new InterpretationEvaluator().evaluate(interpretations);
		doSemanticalAnalysis(this.interpretations, this.formula.getRoot().registerSymbols());
	}
	
	/**
	 * Evaluates a formulas boolean value, according to an interpretation
	 */
	@Override
	public Boolean evaluate() throws EvaluatorException {
		return formula.getRoot().evaluateBoolean(this, interpretations);
	}
}
