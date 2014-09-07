package at.skagen.apps.sat.formula.parser;

import java.util.LinkedList;
import java.util.List;

import static at.skagen.apps.sat.formula.parser.FormulaTokens.*;

public class FormulaLexer {

	public List<Token> lex(String formula) throws LexerException {
		
		String[] stringTokens = formula.split("\\s+|(?<=\\()|(?=\\)|(?=,)|(?==))");
		List<Token> tokens 	  = new LinkedList<Token>();
		
		for (String token : stringTokens) {
			if ("and".equals(token)) {
				tokens.add(new Token(And, token));
			} else if ("or".equals(token)) {
				tokens.add(new Token(Or, token));
			} else if ("if".equals(token)) {
				tokens.add(new Token(If, token));
			} else if ("iff".equals(token)) {
				tokens.add(new Token(Iff, token));
			} else if ("nand".equals(token)) {
				tokens.add(new Token(Nand, token));
			} else if ("nor".equals(token)) {
				tokens.add(new Token(Nor, token));
			} else if ("xor".equals(token)) {
				tokens.add(new Token(Xor, token));
			} else if ("not".equals(token)) {
				tokens.add(new Token(Not, token));
			} else if ("(".equals(token)) {
				tokens.add(new Token(Lparen, token));
			} else if (")".equals(token)) {
				tokens.add(new Token(Rparen, token));
			} else if ("1".equals(token)) {
				tokens.add(new Token(Verum, token));
			} else if ("0".equals(token)) {
				tokens.add(new Token(Falsum, token));
			} else if (token.matches("[A-Z]")) {
				tokens.add(new Token(Variable, token));
			} else {
				throw new LexerException("unknown token " + token);
			}
		}
		
		return tokens;
	}
}
