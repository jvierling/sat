package at.skagen.apps.sat.formula.node;

import java.util.Map;

import at.skagen.apps.sat.formula.evaluation.Evaluator;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;

public interface BooleanEvaluatorNode {

	public boolean evaluateBoolean(Evaluator<?> evaluator, Map<String, Boolean> interpretations) 
			throws EvaluatorException;
}
