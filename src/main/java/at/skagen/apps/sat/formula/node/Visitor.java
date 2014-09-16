package at.skagen.apps.sat.formula.node;

public interface Visitor <R, P> {
	
	public R visit(AndNode formula, P parameter);
	public R visit(OrNode formula, P parameter);
	public R visit(NorNode formula, P parameter);
	public R visit(NandNode formula, P parameter);
	public R visit(IffNode formula, P parameter);
	public R visit(IfNode formula, P parameter);
	public R visit(XorNode formula, P parameter);
	public R visit(NotNode formula, P parameter);
	public R visit(VariableNode formula, P parameter);
	public R visit(ConstantNode formula, P parameter);
	
	public void dispatchVisit(FormulaNode node);
}
