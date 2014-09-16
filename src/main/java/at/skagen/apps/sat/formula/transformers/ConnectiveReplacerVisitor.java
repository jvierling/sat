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

public class ConnectiveReplacerVisitor implements Visitor<Void, Void> {

	private FormulaNode result;
	
	public Void visit(AndNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new AndNode(left, right);
		
		return null;
	}

	public Void visit(OrNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new OrNode(left, right);
		
		return null;
	}

	public Void visit(NandNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new NotNode(new AndNode(left, right));
		
		return null;
	}

	public Void visit(NorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new NotNode(new OrNode(left, right));
		
		return null;
	}

	public Void visit(IffNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new OrNode(new AndNode(new NotNode(left), new NotNode(right)), new AndNode(left, right));
		
		return null;
	}

	public Void visit(XorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new OrNode(new AndNode(new NotNode(left), right), new AndNode(left, new NotNode(right)));
		
		return null;
	}

	public Void visit(IfNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		FormulaNode left = result;
		
		formula.getRightChild().accept(this);
		FormulaNode right = result;
		
		result = new OrNode(new NotNode(left), right);
		
		return null;
		
	}

	public Void visit(NotNode formula, Void p) {
		
		formula.getOperand().accept(this);
		FormulaNode operand = result;
		
		result = new NotNode(operand);
		
		return null;
	}

	public Void visit(VariableNode formula, Void p) {
		result = formula;
		return null;
	}

	public Void visit(ConstantNode formula, Void p) {
		result = formula;
		return null;
	}
	
	public FormulaNode getResult() {
		return result;
	}

	public void dispatchVisit(FormulaNode node) {
		node.accept(this, null);
	}
}
