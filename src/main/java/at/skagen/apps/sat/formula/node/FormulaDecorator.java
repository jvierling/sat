package at.skagen.apps.sat.formula.node;

public abstract class FormulaDecorator implements FormulaNode {

	private FormulaNode formula;
	
	protected FormulaNode getFormula() {
		return formula;
	}
	
	public FormulaDecorator(FormulaNode formula) {
		this.formula = formula;
	}
	
	public void accept(Visitor<?, ?> visitior) {
		formula.accept(visitior);
	}

	public <R, P> R accept(Visitor<R, P> visitor, P parameter) {
		return formula.accept(visitor, parameter);
	}
}
