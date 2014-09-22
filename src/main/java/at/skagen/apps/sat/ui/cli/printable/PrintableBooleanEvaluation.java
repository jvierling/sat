package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.BooleanEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.printer.LinearPrinter;

public class PrintableBooleanEvaluation implements PrintableEvaluation {

	private FormulaNode formula;
	
	private BooleanEvaluation evaluation;
	
	public PrintableBooleanEvaluation(FormulaNode formula, BooleanEvaluation evaluation) {
		this.formula    = formula;
		this.evaluation = evaluation;
	}

	public String evaluate() throws EvaluatorException {
		LinearPrinter printer = new LinearPrinter();
		printer.dispatchVisit(formula);
		return "val(I," + printer.getResult() + ") = " + (evaluation.evaluate() ? "0" : "1");
	}
}
