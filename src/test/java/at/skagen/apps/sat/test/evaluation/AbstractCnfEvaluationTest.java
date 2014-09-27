package at.skagen.apps.sat.test.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import at.skagen.apps.sat.formula.evaluation.CnfSemanticEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;

public class AbstractCnfEvaluationTest {

	private FormulaNode tautology = new OrNode(new ConstantNode(true), new VariableNode("A"));
	
	private FormulaNode formula 
			= new IffNode(new AndNode(new VariableNode("A"),new VariableNode("B")), new VariableNode("C"));
	
	@Test
	public void contradictionShouldEvaluateToConstantVerum() throws EvaluatorException {
		assertTrue(((ConstantNode) new CnfSemanticEvaluation(tautology).evaluate()).getValue());
	}
	
	@Test
	public void dnfFormulaShouldBeEquivalentToFormula() throws EvaluatorException {
		
		FormulaNode cnf = new CnfSemanticEvaluation(formula).evaluate();
		FormulaNode cnfIffFormula = new IffNode(formula, cnf);
		
		assertTrue(((ConstantNode)new CnfSemanticEvaluation(cnfIffFormula).evaluate()).getValue());
	}
}
