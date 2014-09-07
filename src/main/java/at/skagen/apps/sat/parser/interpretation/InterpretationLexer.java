package at.skagen.apps.sat.parser.interpretation;

import java.util.LinkedList;
import java.util.List;

import at.skagen.apps.sat.formula.parser.LexerException;
import static at.skagen.apps.sat.parser.interpretation.Symbols.*;

public class InterpretationLexer {

	public List<Token> lex(String interpretation) throws LexerException {
		
		List<Token> tokens = new LinkedList<Token>();
		
		List<String> stringTokens = tokenize(interpretation, new char[] {'(', ')', '=', ','});
		
		for (String token : stringTokens) {
			if ("I".equals(token)) {
				tokens.add(new Token(Interpretation, token));
			} else if ("(".equals(token)) {
				tokens.add(new Token(Lparen, token));
			} else if (")".equals(token)) {
				tokens.add(new Token(Rparen, token));
			} else if ("=".equals(token)) {
				tokens.add(new Token(Equal, token));
			} else if (",".equals(token)) {
				tokens.add(new Token(Comma, token));
			} else if ("0".equals(token)) {
				tokens.add(new Token(Falsum, token));
			} else if ("1".equals(token)) {
				tokens.add(new Token(Verum, token));
			} else if (token.matches("[A-Z]")) {
				tokens.add(new Token(Variable, token));
			} else {
				throw new LexerException("unexpected token " + token);
			}
		}
		
		return tokens;
	}
	
	private static List<String> tokenize(String input, char[] delimiters) {
		
		List<String> tokens = new LinkedList<String>();
		
		
		
		for (String str : input.split("\\s+")) {
			
			int beginIndex = 0;
			boolean matched = false;
			
			for (int i = 0; i < str.length(); i++) {
				for (char delimiter : delimiters) {
					
					matched = false;
					
					if (str.charAt(i) == delimiter) {
						String beforeDelimiter = str.substring(beginIndex, i);
						if (!beforeDelimiter.isEmpty()) {
							tokens.add(beforeDelimiter);
						}
						tokens.add("" + delimiter);
						beginIndex = i + 1;
						matched = true;
						break;
					}
				}
			}
			if (!matched) {
				tokens.add(str.substring(beginIndex, str.length()));
			}
		}
		
		return tokens;
	} 
}
