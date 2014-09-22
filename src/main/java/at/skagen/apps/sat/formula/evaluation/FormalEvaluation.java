package at.skagen.apps.sat.formula.evaluation;

import java.util.LinkedList;
import java.util.List;

import at.skagen.apps.sat.formula.evaluation.formal.FormalNode;
import at.skagen.apps.sat.formula.evaluation.formal.FormalTransformer;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class FormalEvaluation extends CompleteInterpretedEvaluation<List<String>> {

	private FormalNode formula;
	
	private Interpretation interpretation;
	
	public FormalEvaluation(FormulaNode formula, Interpretation interpretation) {
		FormalTransformer transformer = new FormalTransformer();
		transformer.dispatchVisit(formula);
		this.formula = transformer.getResult();
		this.interpretation = interpretation;
	}
	
	@Override
	public List<String> evaluate() throws EvaluatorException {
		
		doSemanticalAnalysis(interpretation, formula);
		
		List<String> steps = new LinkedList<String>();
		
		for (int limit = 0; !formula.isEvaluated(); limit++) {
			steps.add(formula.evaluateFormal(interpretation, limit));
		}
		
		return steps;
	}
}
