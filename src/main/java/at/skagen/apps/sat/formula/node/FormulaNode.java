package at.skagen.apps.sat.formula.node;

/*
 * Semantic checking right after parsing ? This needs to be done in the evaluator.
 * 
 */

public interface FormulaNode {
	
	public void accept(Visitor<?, ?> visitior);
	
	public <R, P> R accept(Visitor<R, P> visitor, P parameter);
}
