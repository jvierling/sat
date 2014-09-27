package at.skagen.apps.sat.test.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import at.skagen.apps.sat.formula.evaluation.DnfSemanticEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.node.XorNode;

public class AbstractDnfEvaluationTest {

	private FormulaNode contradiction = new AndNode(new ConstantNode(false), new VariableNode("A"));
	
	private FormulaNode formula 
			= new IffNode(new AndNode(new VariableNode("A"),new VariableNode("B")), new VariableNode("C"));
	
	@Test
	public void contradictionShouldEvaluateToConstantFalsum() throws EvaluatorException {
		assertFalse(((ConstantNode) new DnfSemanticEvaluation(contradiction).evaluate()).getValue());
	}
	
	@Test
	public void dnfFormulaShouldBeEquivalentToFormula() throws EvaluatorException {
		
		FormulaNode dnf = new DnfSemanticEvaluation(formula).evaluate();
		FormulaNode dnfXorFormula = new XorNode(formula, dnf);
		
		assertFalse(((ConstantNode)new DnfSemanticEvaluation(dnfXorFormula).evaluate()).getValue());
	}
}
