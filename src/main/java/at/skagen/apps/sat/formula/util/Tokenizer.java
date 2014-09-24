package at.skagen.apps.sat.formula.util;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

	private String[] delimiters;
	
	public Tokenizer(String ... delimiters) {
		this.delimiters = delimiters;
	}
	
	public List<String> tokenize(String input) {
		
		List<String> tokens = new LinkedList<String>();
		
		for (String token : input.split("\\s+")) {
			tokens.add(token);
		}
		
		for (String delimiter : delimiters) {
			tokens = splitAroundDelimiter(tokens, delimiter);
		}
		
		return tokens;
	}
	
	private List<String> splitAroundDelimiter(List<String> inputs, String delimiter) {
		
		List<String> tokens = new LinkedList<String>();
		
		for (String input : inputs) {
			for (String token : input.split("(?<=" + delimiter + ")|(?=" + delimiter + ")")) {
				if (!token.isEmpty()) {
					tokens.add(token);
				}
			}
		}
		
		return tokens;
	}
}
