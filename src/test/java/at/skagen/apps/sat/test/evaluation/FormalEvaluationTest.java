package at.skagen.apps.sat.test.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import at.skagen.apps.sat.formula.evaluation.BooleanEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class FormalEvaluationTest {

	private Map<String, Boolean> i0 = new HashMap<String,Boolean>();
	{
		i0.put("A", false);
		i0.put("B", false);
	}
	
	private AndNode andFormula = new AndNode(new VariableNode("A"), new VariableNode("B"));
	private NotNode notFormula = new NotNode(new VariableNode("A"));
	
	@Test
	public void evaluationShouldThrowExceptionOnUndefinedVariables() {
		Interpretation interpretation = new Interpretation(new HashMap<String, Boolean>());
		try {
			new BooleanEvaluation(andFormula, interpretation).evaluate();
			fail();
		} catch (EvaluatorException e) {
			assertEquals("Error : undefined variable A", e.getMessage());
		}
	}
	
	@Test
	public void evaluationShouldThrowExceptionOnUnusedVariables() {
		Interpretation interpretation = new Interpretation(i0);
		try {
			new BooleanEvaluation(notFormula, interpretation).evaluate();
			fail();
		} catch (EvaluatorException e) {
			assertEquals("Error : unused variable B", e.getMessage());
		}
	}
}
