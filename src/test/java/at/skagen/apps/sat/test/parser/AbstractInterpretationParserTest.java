package at.skagen.apps.sat.test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.parser.interpretation.Interpretation;
import at.skagen.apps.sat.parser.interpretation.InterpretationParser;

public class AbstractInterpretationParserTest {

	@Test
	public void parserShouldThrowExceptionOnSyntaxError() {
		// Invalid interpretation name
		try {
			new InterpretationParser().parse("J=(A=0, B=1)");
			fail();
		} catch (ParserException e) { }		
		// Invalid variable name
		try {
			new InterpretationParser().parse("I=(A=0, b=1)");
			fail();
		} catch (ParserException e) { }
		// Invalid boolean value
		try {
			new InterpretationParser().parse("I=(A=false, B=1)");
			fail();
		} catch (ParserException e) { }
		// Invalid parentheses
		try {
			new InterpretationParser().parse("I=(A=0, B=1");
			fail();
		} catch (ParserException e) { }
		try {
			new InterpretationParser().parse("I= A=0, B=1)");
			fail();
		} catch (ParserException e) { }
		// Missing ,
		try {
			new InterpretationParser().parse("I=(A=0 B=1)");
			fail();
		} catch (ParserException e) { }
	}
	
	@Test
	public void parserShouldThrowExceptionOnFirstSyntaxError() {
		try {
			new InterpretationParser().parse("J=(A=0, B=1");
			fail();
		} catch (ParserException e) {
			assertEquals("Syntax error : expected token 'I' but got J", e.getMessage());
		}
	}
	
	@Test
	public void parserShouldThrowExceptionOnMultipleVariableOccurrence() {
		try {
			new InterpretationParser().parse("I=(A=0, A=1)");
			fail();
		} catch (ParserException e) {
			assertEquals("Syntax error : multiple occurrences of variable A",e.getMessage());
		}
	}
	
	@Test
	public void parserShouldProduceCompleteCorrectVariableMapping() throws ParserException {
		Interpretation interpretation = new InterpretationParser().parse("I=(A=0, B=1, C=0)");
		
		assertEquals(3, interpretation.getDefinedVariables().size());
		assertTrue(interpretation.getDefinedVariables().contains("A"));
		assertTrue(interpretation.getDefinedVariables().contains("B"));
		assertTrue(interpretation.getDefinedVariables().contains("C"));
		
		assertEquals(false, interpretation.getVariableInterpretation(new VariableNode("A")));
		assertEquals(true,  interpretation.getVariableInterpretation(new VariableNode("B")));
		assertEquals(false, interpretation.getVariableInterpretation(new VariableNode("C")));
	}
}
