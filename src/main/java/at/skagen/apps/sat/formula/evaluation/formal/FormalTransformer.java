package at.skagen.apps.sat.formula.evaluation.formal;

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

public class FormalTransformer implements Visitor<FormalNode, Void> {

	private FormalNode result = null;
	
	public FormalNode visit(AndNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "AND");
	}

	public FormalNode visit(OrNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "OR");
	}

	public FormalNode visit(NorNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "NOR");
	}

	public FormalNode visit(NandNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "NAND");
	}

	public FormalNode visit(IffNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "IFF");
	}

	public FormalNode visit(IfNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "IF");
	}

	public FormalNode visit(XorNode formula, Void parameter) {
		FormalNode left  = formula.getLeftChild().accept(this, parameter);
		FormalNode right = formula.getRightChild().accept(this, parameter);
		return new BinaryFormal(formula, left, right, "XOR");
	}

	public FormalNode visit(NotNode formula, Void parameter) {
		FormalNode operand  = formula.getOperand().accept(this, parameter);
		return new UnaryFormal(formula, operand, "NOT");
	}

	public FormalNode visit(VariableNode formula, Void parameter) {
		return new VariableFormal(formula);
	}

	public FormalNode visit(ConstantNode formula, Void parameter) {
		return new ConstantFormal(formula);
	}

	public void dispatchVisit(FormulaNode node) {
		result = null;
		result = node.accept(this, null);
	}

	public FormalNode getResult() {
		return result;
	}
}
