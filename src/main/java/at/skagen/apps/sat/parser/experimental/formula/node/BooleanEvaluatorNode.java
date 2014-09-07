package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.Map;

import at.skagen.apps.parser.experimental.Evaluator;
import at.skagen.apps.sat.parser.formula.EvaluatorException;

public interface BooleanEvaluatorNode {

	public boolean evaluateBoolean(Evaluator<?> evaluator, Map<String, Boolean> interpretations) 
			throws EvaluatorException;
}
