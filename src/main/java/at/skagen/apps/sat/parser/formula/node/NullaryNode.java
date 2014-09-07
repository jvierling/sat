package at.skagen.apps.sat.parser.formula.node;

public abstract class NullaryNode extends FormulaNode {

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
