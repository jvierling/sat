package at.skagen.apps.sat.formula.transformers;

import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.IfNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.NandNode;
import at.skagen.apps.sat.formula.node.NorNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.node.Visitor;
import at.skagen.apps.sat.formula.node.XorNode;
import at.skagen.apps.sat.formula.pattern.DoubleNegationMatcher;

public class DoubleNegationEliminer implements Visitor<FormulaNode, Void> {

	private final DoubleNegationEliminer doubleNegationEliminer = this;
	
	private FormulaNode result = null;
	
	public FormulaNode visit(AndNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new AndNode(left, right);
	}

	public FormulaNode visit(OrNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new OrNode(left, right);
	}

	public FormulaNode visit(NorNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new NorNode(left, right);
	}

	public FormulaNode visit(NandNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new NandNode(left, right);
	}

	public FormulaNode visit(IffNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new IffNode(left, right);
	}

	public FormulaNode visit(IfNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new IfNode(left, right);
	}

	public FormulaNode visit(XorNode formula, Void parameter) {
		FormulaNode left  = formula.getLeftChild().accept(doubleNegationEliminer, null);
		FormulaNode right = formula.getRightChild().accept(doubleNegationEliminer, null);
		
		return new XorNode(left, right);
	}

	public FormulaNode visit(NotNode formula, Void parameter) {

		DoubleNegationMatcher matcher = new DoubleNegationMatcher();
		
		matcher.dispatchVisit(formula);
		if (matcher.hasMatched()) {
			return matcher.getF().accept(doubleNegationEliminer, null);
		}
		
		return new NotNode(formula.getOperand().accept(doubleNegationEliminer, null));
	}

	public FormulaNode visit(VariableNode formula, Void parameter) {
		return formula;
	}

	public FormulaNode visit(ConstantNode formula, Void parameter) {
		return formula;
	}

	public void dispatchVisit(FormulaNode node) {
		result = node.accept(this, null);
	}

	public FormulaNode getResult() {
		return result;
	}
}
