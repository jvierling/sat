package at.skagen.apps.sat.formula.evaluation;

import java.util.Set;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.util.VariableLister;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public abstract class CompleteInterpretedEvaluation<T> implements InterpretedEvaluation<T> {

	/**
	 * Evaluates a formula according to a completely specified interpretation.
	 */
	public abstract T evaluate() throws EvaluatorException;
	
	protected final void doSemanticalAnalysis(Interpretation interpetations, FormulaNode formula)
			throws EvaluatorException {
		
		VariableLister lister = new VariableLister();
		
		lister.dispatchVisit(formula);
		
		Set<String> identifiers = lister.getResult();
		
		for (String identifier : identifiers) {
			if (!interpetations.getDefinedVariables().contains(identifier)) {
				throw new EvaluatorException("undefined variable " + identifier);
			}
		}
		for (String identifier : interpetations.getDefinedVariables()) {
			if (!identifiers.contains(identifier)) {
				throw new EvaluatorException("unused variable " + identifier);
			}
		}
	}
}
