package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.FormalEvaluation;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.printer.LinearPrinter;

public class PrintableFormalEvaluation implements PrintableEvaluation {

	private FormulaNode formula;
	
	private FormalEvaluation evaluation;
	
	public PrintableFormalEvaluation(FormulaNode formula, FormalEvaluation evaluation) {
		this.formula = formula;
		this.evaluation = evaluation;
	}

	public String evaluate() throws EvaluatorException {
	
		LinearPrinter printer = new LinearPrinter();
		printer.dispatchVisit(formula);
		
		String result = "val(I, " + printer.getResult() + ")" + "\n";
		
		for (String line : evaluation.evaluate()) {
			result += " = " + line + "\n";
		}
		
		return result.substring(0, result.length() - 1);
	}
}
