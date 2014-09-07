package at.skagen.apps.parser.experimental;

import at.skagen.apps.sat.parser.formula.EvaluatorException;

public abstract class PartialInterpretedEvaluation<T> extends InterpretedEvaluation<T> {

	/**
	 * Evaluates a formula according to a partially specified interpretation.
	 */
	@Override
	public abstract T evaluate() throws EvaluatorException;
}
