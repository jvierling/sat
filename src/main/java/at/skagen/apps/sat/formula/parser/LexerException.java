package at.skagen.apps.sat.formula.parser;

public class LexerException extends Exception {

	private static final long serialVersionUID = 1L;

	public LexerException(String message) {
		super("Invalid token : " + message);
	}
}
