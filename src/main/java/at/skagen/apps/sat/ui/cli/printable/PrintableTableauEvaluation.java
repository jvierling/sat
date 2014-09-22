package at.skagen.apps.sat.ui.cli.printable;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.tableau.TableauEvaluation;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.ui.cli.TableauRenderer;

public class PrintableTableauEvaluation implements PrintableEvaluation {
	
	private TableauEvaluation evaluation;
	
	public PrintableTableauEvaluation(FormulaNode formula) {
		this.evaluation = new TableauEvaluation(formula);
	}

	public String evaluate() throws EvaluatorException {
		return new TableauRenderer().renderTableau(evaluation.evaluate());
	}
	
}
