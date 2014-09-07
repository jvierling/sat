package at.skagen.apps.sat.formula.node;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.FormalEvaluation;

public interface FormalEvaluatorNode {

	public String evaluateFormal(FormalEvaluation evaluator, int limit) throws EvaluatorException;
	
	public void setEvaluated(boolean isEvaluated);
	
	public boolean isEvaluated();
}
