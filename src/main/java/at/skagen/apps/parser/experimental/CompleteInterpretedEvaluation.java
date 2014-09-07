package at.skagen.apps.parser.experimental;

import java.util.Map;
import java.util.Set;

import at.skagen.apps.sat.parser.formula.EvaluatorException;

public abstract class CompleteInterpretedEvaluation<T> extends InterpretedEvaluation<T> {

	/**
	 * Evaluates a formula according to a completely specified interpretation.
	 */
	@Override
	public abstract T evaluate() throws EvaluatorException;
	
	protected final void doSemanticalAnalysis(Map<String, Boolean> interpetations, Set<String> identifiers) 
			throws EvaluatorException {
		for (String identifier : identifiers) {
			if (!interpetations.keySet().contains(identifier)) {
				throw new EvaluatorException("undefined variable " + identifier);
			}
		}
		for (String identifier : interpetations.keySet()) {
			if (!identifiers.contains(identifier)) {
				throw new EvaluatorException("unused variable " + identifier);
			}
		}
	}
}
