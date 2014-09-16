package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.evaluation.BooleanEvaluator;
import at.skagen.apps.sat.formula.node.FormulaDecorator;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.printer.InfixPrinter;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public abstract class FormalNode extends FormulaDecorator {

	private boolean evaluated = false;
	
	public FormalNode(FormulaNode formula) {
		super(formula);
	}
	
	public abstract String evaluateFormal(Interpretation interpretation, int limit);
	
	public boolean isEvaluated() {
		return evaluated;
	}
	
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}
	
	public final String toString() {
		return printFormula(this);
	}
	
	protected String printFormula(FormulaNode formula) {
		InfixPrinter printer = new InfixPrinter();
		printer.dispatchVisit(formula);
		return printer.getResult();
	}
	
	protected String evaluateFormula(FormulaNode formula, Interpretation interpretation) {
		BooleanEvaluator evaluator = new BooleanEvaluator(interpretation);
		evaluator.dispatchVisit(formula);
		return evaluator.getResult() ? "1" : "0";
	}
}
