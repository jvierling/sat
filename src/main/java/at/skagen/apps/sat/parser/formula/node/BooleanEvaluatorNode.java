package at.skagen.apps.sat.parser.formula.node;

import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.FormulaEvaluator;

public interface BooleanEvaluatorNode {

	public boolean evaluate(FormulaEvaluator<?> evaluator) throws EvaluatorException;
}
