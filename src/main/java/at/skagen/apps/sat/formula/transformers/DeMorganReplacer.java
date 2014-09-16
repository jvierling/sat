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
import at.skagen.apps.sat.formula.pattern.AndDeMorganMatcher;
import at.skagen.apps.sat.formula.pattern.OrDeMorganMatcher;

public class DeMorganReplacer implements Visitor<FormulaNode, Void> {

	private FormulaNode result = null;
	
	public FormulaNode visit(AndNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new AndNode(left, right);
	}

	public FormulaNode visit(OrNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new OrNode(left, right);
	}

	public FormulaNode visit(NorNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new NorNode(left, right);
	}

	public FormulaNode visit(NandNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new NandNode(left, right);
	}

	public FormulaNode visit(IffNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new IffNode(left, right);
	}

	public FormulaNode visit(IfNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new IfNode(left, right);
	}

	public FormulaNode visit(XorNode formula, Void parameter) {
		
		FormulaNode left  = formula.getLeftChild().accept(this, null) ;
		FormulaNode right = formula.getRightChild().accept(this, null) ;
		
		return new XorNode(left, right);
	}

	public FormulaNode visit(NotNode formula, Void parameter) {
		
		AndDeMorganMatcher andMatcher = new AndDeMorganMatcher();
		OrDeMorganMatcher orMatcher   = new OrDeMorganMatcher();
		
		andMatcher.dispatchVisit(formula);
		if (andMatcher.hasMatched()) {
			return new OrNode(new NotNode(andMatcher.getF()), new NotNode(andMatcher.getG()));
		}
		
		orMatcher.dispatchVisit(formula);
		if (orMatcher.hasMatched()) {
			return new AndNode(new NotNode(orMatcher.getF()), new NotNode(orMatcher.getG()));
		}
		
		
		return new NotNode(formula.getOperand().accept(this, null));
	}

	public FormulaNode visit(VariableNode formula, Void parameter) {
		return formula;
	}

	public FormulaNode visit(ConstantNode formula, Void parameter) {
		return formula;
	}

	public void dispatchVisit(FormulaNode node) {
		result = null;
		result = node.accept(this, null);
	}

	public FormulaNode getResult() {
		return result;
	}
}
