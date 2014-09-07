package at.skagen.apps.sat.formula.evaluation;


public abstract class InterpretedEvaluation<T> extends Evaluator<T> {

	/**
	 * Evaluates this evaluation according to an interpretation
	 */
	@Override
	public abstract T evaluate() throws EvaluatorException;
}
