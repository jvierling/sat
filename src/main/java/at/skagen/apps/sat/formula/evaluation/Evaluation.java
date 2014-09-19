package at.skagen.apps.sat.formula.evaluation;

public interface Evaluation<T> {

	/**
	 * Evaluates this evaluation.
	 * 
	 * @return the evaluation result
	 * @throws EvaluatorException if an error occured during evaluation
	 */
	public abstract T evaluate() throws EvaluatorException;
}
