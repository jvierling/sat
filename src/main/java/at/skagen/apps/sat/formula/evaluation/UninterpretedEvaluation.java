package at.skagen.apps.sat.formula.evaluation;


public interface UninterpretedEvaluation<T> extends Evaluation<T> {

	/**
	 * Evaluates a formula without using a variable interpretation.
	 */
	public abstract T evaluate() throws EvaluatorException;
}
