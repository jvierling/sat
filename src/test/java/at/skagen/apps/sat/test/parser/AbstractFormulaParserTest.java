package at.skagen.apps.sat.test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import at.skagen.apps.sat.formula.parser.FormulaParser;
import at.skagen.apps.sat.formula.parser.ParserException;

public class AbstractFormulaParserTest {

	private FormulaParser parser = new FormulaParser();
	
	@Test
	public void parserThrowsExceptionOnSyntaxError() {
		// invalid variable name
		try {
			parser.parse("A or b");
			fail();
		} catch (ParserException e) { }
		// invalid operator
		try {
			parser.parse("A OR B");
			fail();
		} catch (ParserException e) { }
		// invalid truth constant
		try {
			parser.parse("A or true");
			fail();
		} catch (ParserException e) { }
		// invalid parentheses
		try {
			parser.parse("[A or B]");
			fail();
		} catch (ParserException e) { }
		// non well formed parentheses
		try {
			parser.parse("A or B)");
			fail();
		} catch (ParserException e) { }
		try {
			parser.parse("(A or B");
			fail();
		} catch (ParserException e) { }
		try {
			parser.parse("A or B)");
			fail();
		} catch (ParserException e) { }
		// malformed formula
		try {
			parser.parse("A or");
			fail();
		} catch (ParserException e) { }
	}
}
