package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.DnfSemanticEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.printer.LinearPrinter;

public class PrintableDnfEvaluation implements PrintableEvaluation {

	private FormulaNode formula;
	
	private DnfSemanticEvaluation evaluation;
	
	public PrintableDnfEvaluation(FormulaNode formula) {
		this.formula = formula;
		this.evaluation = new DnfSemanticEvaluation(formula);
	}

	public String evaluate() throws EvaluatorException {
		String result = "";
		LinearPrinter printer = new LinearPrinter();
		printer.dispatchVisit(formula);
		result = printer.getResult();
		printer.dispatchVisit(evaluation.evaluate());
		result += " = " + printer.getResult();
		return result;
	}
}
