package at.skagen.apps.sat.formula.evaluation;

public class EvaluatorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EvaluatorException(String message) {
		super("Error : " + message);
	}
}
