package at.skagen.apps.sat.parser.formula.node;

import at.skagen.apps.sat.parser.formula.EvaluatorException;
import at.skagen.apps.sat.parser.formula.StepEvaluator;

public interface StepEvaluatorNode {

	public String evaluate(StepEvaluator evaluator, int limit) throws EvaluatorException;
	
	public void setEvaluated(boolean isEvaluated);
	
	public boolean isEvaluated();
}
