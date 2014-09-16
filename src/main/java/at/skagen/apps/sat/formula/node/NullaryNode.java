package at.skagen.apps.sat.formula.node;

public abstract class NullaryNode implements FormulaNode {

	private String symbol;
	
	public NullaryNode(String symbol) {
		this.symbol = symbol;
	}
	
	public int depth() {
		return 1;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
}
