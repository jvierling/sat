package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;

public interface PrintableEvaluation {

	public String evaluate() throws EvaluatorException;
}
