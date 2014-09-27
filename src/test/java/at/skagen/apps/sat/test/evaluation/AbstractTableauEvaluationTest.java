package at.skagen.apps.sat.test.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.tableau.TableauEvaluation;
import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.ui.cli.TableauRenderer;

public class AbstractTableauEvaluationTest {

	private FormulaNode satisfiableFormula = new AndNode(new VariableNode("A"), 
			new AndNode(new VariableNode("B"), new AndNode(new VariableNode("C"), new VariableNode("D")))); 
	private FormulaNode contradiction      = new AndNode(satisfiableFormula, new ConstantNode(false));
	
	@Test
	public void satisfiableFormulaShouldEvaluateToClosedTableau() throws EvaluatorException {
		System.out.println(new TableauRenderer().renderTableau(new TableauEvaluation(satisfiableFormula).evaluate()));
		System.out.println(new TableauEvaluation(satisfiableFormula).evaluate().isClosed());
		assertTrue(new TableauEvaluation(satisfiableFormula).evaluate().isClosed());
	}
	
	@Test
	public void contradictionFormulaShouldEvaluateToUnclosedTableau() throws EvaluatorException {
		assertFalse(new TableauEvaluation(contradiction).evaluate().isClosed());
	}
}
