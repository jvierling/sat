package at.skagen.apps.parser.experimental;

import at.skagen.apps.sat.parser.experimental.formula.node.FormulaNode;

public class ParseTree {

	private FormulaNode root;
	
	public ParseTree(FormulaNode root) {
		this.root = root;
	}
	
	public FormulaNode getRoot() {
		return root;
	}
}
