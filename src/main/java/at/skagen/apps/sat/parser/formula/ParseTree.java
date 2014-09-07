package at.skagen.apps.sat.parser.formula;

import at.skagen.apps.sat.parser.formula.node.FormulaNode;

public class ParseTree {

	private FormulaNode root;
	
	public ParseTree(FormulaNode root) {
		this.root = root;
	}
	
	public FormulaNode getRoot() {
		return root;
	}
}
