package at.skagen.apps.sat.parser.formula;

public class ParserException extends Exception {

	private static final long serialVersionUID = 1L;

	public ParserException(String message) {
		super("Syntax error : " + message);
	}
}
