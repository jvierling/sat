package at.skagen.apps.parser.experimental;

import at.skagen.apps.sat.parser.formula.EvaluatorException;

public abstract class UninterpretedEvaluation<T> extends Evaluator<T> {

	/**
	 * Evaluates a formula without using a variable interpretation.
	 */
	public abstract T evaluate() throws EvaluatorException;
}
