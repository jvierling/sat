package at.skagen.apps.sat.formula.evaluation;


public abstract class PartialInterpretedEvaluation<T> extends InterpretedEvaluation<T> {

	/**
	 * Evaluates a formula according to a partially specified interpretation.
	 */
	@Override
	public abstract T evaluate() throws EvaluatorException;
}
