package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.CnfSemanticEvaluation;
import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.printer.LinearPrinter;

public class PrintableCnfEvaluation implements PrintableEvaluation {

	private FormulaNode formula;
	
	private CnfSemanticEvaluation evaluation;
	
	public PrintableCnfEvaluation(FormulaNode formula) {
		this.formula = formula;
		this.evaluation = new CnfSemanticEvaluation(formula);
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
