package at.skagen.apps.sat.parser.experimental.formula.node;

import at.skagen.apps.parser.experimental.FormalEvaluation;
import at.skagen.apps.sat.parser.formula.EvaluatorException;

public interface FormalEvaluatorNode {

	public String evaluateFormal(FormalEvaluation evaluator, int limit) throws EvaluatorException;
	
	public void setEvaluated(boolean isEvaluated);
	
	public boolean isEvaluated();
}
