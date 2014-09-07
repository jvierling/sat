package at.skagen.apps.sat.parser.formula;

public class EvaluatorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EvaluatorException(String message) {
		super("Error : " + message);
	}
}
