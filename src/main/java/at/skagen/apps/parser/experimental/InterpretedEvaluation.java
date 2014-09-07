package at.skagen.apps.parser.experimental;

import at.skagen.apps.sat.parser.formula.EvaluatorException;

public abstract class InterpretedEvaluation<T> extends Evaluator<T> {

	/**
	 * Evaluates this evaluation according to an interpretation
	 */
	@Override
	public abstract T evaluate() throws EvaluatorException;
}
