package at.skagen.apps.sat.formula.evaluation;


public interface PartialInterpretedEvaluation<T> extends InterpretedEvaluation<T> {

	/**
	 * Evaluates a formula according to a partially specified interpretation.
	 */
	public abstract T evaluate() throws EvaluatorException;
}
