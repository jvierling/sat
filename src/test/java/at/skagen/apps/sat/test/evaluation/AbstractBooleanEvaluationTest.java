package at.skagen.apps.sat.test.evaluation;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import at.skagen.apps.sat.formula.evaluation.BooleanEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.IfNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.NandNode;
import at.skagen.apps.sat.formula.node.NorNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.node.XorNode;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class AbstractBooleanEvaluationTest {
	
	private Map<String, Boolean> i0 = new HashMap<String,Boolean>();
	{
		i0.put("A", false);
		i0.put("B", false);
	}
	
	private Map<String, Boolean> i1 = new HashMap<String,Boolean>();
	{
		i1.put("A", false);
		i1.put("B", true);
	}
	
	
	private Map<String, Boolean> i2 = new HashMap<String,Boolean>();
	{
		i2.put("A", true);
		i2.put("B", false);
	}
	
	private Map<String, Boolean> i3 = new HashMap<String,Boolean>();
	{
		i3.put("A", true);
		i3.put("B", true);
	}
	
	private AndNode andFormula = new AndNode(new VariableNode("A"), new VariableNode("B"));
	private OrNode orFormula = new OrNode(new VariableNode("A"), new VariableNode("B"));
	private IffNode iffFormula = new IffNode(new VariableNode("A"), new VariableNode("B"));
	private IfNode ifFormula = new IfNode(new VariableNode("A"), new VariableNode("B"));
	private XorNode xorFormula = new XorNode(new VariableNode("A"), new VariableNode("B"));
	private NandNode nandFormula = new NandNode(new VariableNode("A"), new VariableNode("B"));
	private NorNode norFormula = new NorNode(new VariableNode("A"), new VariableNode("B"));
	
	private NotNode notFormula = new NotNode(new VariableNode("A"));
	
	private ConstantNode verumFormula = new ConstantNode(true);
	
	private ConstantNode falsumFormula = new ConstantNode(false);
	
	@Test
	public void binaryOperatorsShouldBeEvaluatedCorrectly() throws EvaluatorException {
		// and
		assertEquals(false, new BooleanEvaluation(andFormula, new Interpretation(i0)).evaluate());
		assertEquals(false, new BooleanEvaluation(andFormula, new Interpretation(i1)).evaluate());
		assertEquals(false, new BooleanEvaluation(andFormula, new Interpretation(i2)).evaluate());
		assertEquals(true,  new BooleanEvaluation(andFormula, new Interpretation(i3)).evaluate());
		// or
		assertEquals(false,new BooleanEvaluation(orFormula, new Interpretation(i0)).evaluate());
		assertEquals(true, new BooleanEvaluation(orFormula, new Interpretation(i1)).evaluate());
		assertEquals(true, new BooleanEvaluation(orFormula, new Interpretation(i2)).evaluate());
		assertEquals(true, new BooleanEvaluation(orFormula, new Interpretation(i3)).evaluate());
		// nand
		assertEquals(true, new BooleanEvaluation(nandFormula, new Interpretation(i0)).evaluate());
		assertEquals(true, new BooleanEvaluation(nandFormula, new Interpretation(i1)).evaluate());
		assertEquals(true, new BooleanEvaluation(nandFormula, new Interpretation(i2)).evaluate());
		assertEquals(false,new BooleanEvaluation(nandFormula, new Interpretation(i3)).evaluate());
		// iff
		assertEquals(true, new BooleanEvaluation(iffFormula, new Interpretation(i0)).evaluate());
		assertEquals(false,new BooleanEvaluation(iffFormula, new Interpretation(i1)).evaluate());
		assertEquals(false,new BooleanEvaluation(iffFormula, new Interpretation(i2)).evaluate());
		assertEquals(true, new BooleanEvaluation(iffFormula, new Interpretation(i3)).evaluate());
		// if
		assertEquals(true, new BooleanEvaluation(ifFormula, new Interpretation(i0)).evaluate());
		assertEquals(true, new BooleanEvaluation(ifFormula, new Interpretation(i1)).evaluate());
		assertEquals(false,new BooleanEvaluation(ifFormula, new Interpretation(i2)).evaluate());
		assertEquals(true, new BooleanEvaluation(ifFormula, new Interpretation(i3)).evaluate());
		// nor 
		assertEquals(true, new BooleanEvaluation(norFormula, new Interpretation(i0)).evaluate());
		assertEquals(false,new BooleanEvaluation(norFormula, new Interpretation(i1)).evaluate());
		assertEquals(false,new BooleanEvaluation(norFormula, new Interpretation(i2)).evaluate());
		assertEquals(false,new BooleanEvaluation(norFormula, new Interpretation(i3)).evaluate());
		// xor
		assertEquals(false,new BooleanEvaluation(xorFormula, new Interpretation(i0)).evaluate());
		assertEquals(true, new BooleanEvaluation(xorFormula, new Interpretation(i1)).evaluate());
		assertEquals(true, new BooleanEvaluation(xorFormula, new Interpretation(i2)).evaluate());
		assertEquals(false,new BooleanEvaluation(xorFormula, new Interpretation(i3)).evaluate());
	}
	
	@Test
	public void notOperatorShouldBeEvaluatedCorrectly() throws EvaluatorException {
		Map<String, Boolean> i = new HashMap<String, Boolean>();
		
		i.put("A", false);
		assertEquals(true, new BooleanEvaluation(notFormula, new Interpretation(i)).evaluate());
		
		i.put("A", true);
		assertEquals(false, new BooleanEvaluation(notFormula, new Interpretation(i)).evaluate());
	}
	
	@Test
	public void constantNodeShouldBeEvaluatedCorrectly() throws EvaluatorException {
		Interpretation interpretation = new Interpretation(new HashMap<String, Boolean>());
		assertEquals(true, new BooleanEvaluation(verumFormula, interpretation).evaluate());
		assertEquals(false, new BooleanEvaluation(falsumFormula, interpretation).evaluate());
	}
	
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
