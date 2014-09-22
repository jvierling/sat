package at.skagen.apps.sat.parser.interpretation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.skagen.apps.sat.formula.parser.LexerException;
import at.skagen.apps.sat.formula.parser.ParserException;
import static at.skagen.apps.sat.parser.interpretation.Symbols.*;

public class InterpretationParser {

	private Iterator<Token> tokens;
	
	private Token currentToken = null;
	
	public Interpretation parse(String interpretation) throws ParserException {
		
		InterpretationNode result = null;
		
		try {
			tokens = new InterpretationLexer().lex(interpretation).iterator();
			
			if (!tokens.hasNext()) {
				throw new ParserException("syntax error : expected interpretation definition");
			}
			currentToken = tokens.next();
			
			result = interpretation();
			
		} catch (LexerException e) {
			throw new ParserException(e.getMessage());
		}
		
		return buildInterpretation(result);
	}
	
	public Interpretation buildInterpretation(InterpretationNode node) throws ParserException {
		
		HashMap<String, Boolean> variableTable = new HashMap<String, Boolean>();
		
		for (VariableInterpretationNode variable : node.getChildren()) {
			if (variableTable.get(variable.getIdentifier()) != null) {
				throw new ParserException("multiple occurrences of variable " + variable.getIdentifier());
			}
			
			boolean value = variable.getValue() == Verum ? true : false;
			
			variableTable.put(variable.getIdentifier(), value);
		}
		
		return new Interpretation(variableTable);
	}
	
	private boolean accept(Symbols symbol) {
		if (currentToken == null) {
			return false;
		}
		if (symbol.equals(currentToken.getSymbol())) {
			if (tokens.hasNext()) {
				currentToken  = tokens.next();
			} else {
				currentToken = null;
			}
			return true;
		}
		return false;
	}
	
	private boolean match(Symbols symbol) {
		if (currentToken == null) {
			return false;
		}
		if (symbol.equals(currentToken.getSymbol())) {
			return true;
		}
		return false;
	}
	
	private InterpretationNode interpretation() throws ParserException {
		
		if (!accept(Interpretation)) {
			throw new ParserException("expected token 'I' but got " + currentToken);
		}
		if (!accept(Equal)) {
			throw new ParserException("expected token '=' but got " + currentToken);
		}
		if (!accept(Lparen)) {
			throw new ParserException("expected token '(' but got " + currentToken);
		}
		
		InterpretationNode node = new InterpretationNode();
		
		node.addAllChildren(variableInterpretationList());
		
		if (!accept(Rparen)) {
			throw new ParserException("expected token ')' but got " + currentToken);
		}
		
		return node;
	}
	
	private List<VariableInterpretationNode> variableInterpretationList() throws ParserException {
		
		List<VariableInterpretationNode> nodes = new LinkedList<VariableInterpretationNode>();
		
		if (match(Variable)) {
			nodes.add(variableInterpretation());
			while(accept(Comma)) {
				nodes.add(variableInterpretation());
			}
		}
		
		return nodes;
	}
	
	private VariableInterpretationNode variableInterpretation() throws ParserException {
		
		Token varToken = currentToken;
		if (!accept(Variable)) {
			throw new ParserException("expected variable but got " + currentToken.getContent());
		}
		if (!accept(Equal)) {
			throw new ParserException("expected '=' but got " + currentToken.getContent());
		}
		
		Token valueToken = currentToken;
		if (!accept(Verum) && !accept(Falsum)) {
			throw new ParserException("expected 0 or 1 but got " + currentToken.getContent());
		}
		
		return new VariableInterpretationNode(varToken.getContent(), valueToken.getSymbol());
	}
	
	public static void main(String[] args) {
		try {
			new InterpretationParser().parse("4 I=(A=1,)");
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
