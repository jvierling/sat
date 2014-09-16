package at.skagen.apps.sat.formula.evaluation;


public interface InterpretedEvaluation<T> extends Evaluator<T> {

	/**
	 * Evaluates this evaluation according to an interpretation
	 */
	public abstract T evaluate() throws EvaluatorException;
}
