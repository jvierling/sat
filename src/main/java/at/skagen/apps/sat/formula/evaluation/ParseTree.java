package at.skagen.apps.sat.formula.evaluation;

import at.skagen.apps.sat.formula.node.FormulaNode;

public class ParseTree {

	private FormulaNode root;
	
	public ParseTree(FormulaNode root) {
		this.root = root;
	}
	
	public FormulaNode getRoot() {
		return root;
	}
}
