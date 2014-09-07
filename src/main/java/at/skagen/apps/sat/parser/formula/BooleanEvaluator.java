package at.skagen.apps.sat.parser.formula;

public class BooleanEvaluator extends FormulaEvaluator<Boolean> {
	
	public BooleanEvaluator(String formula, String interpretation) throws EvaluatorException {
		super(formula, interpretation);
	}
	
	@Override
	protected Boolean evaluateFormula(ParseTree formula) throws EvaluatorException {
		return evaluate(formula.getRoot());
	}
}
