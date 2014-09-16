package at.skagen.apps.sat.formula.printer;

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

public class PostfixPrinter implements Visitor<String, Void> {

	private String result = "";
	
	public String visit(AndNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "and";
	}

	public String visit(OrNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "or";
	}

	public String visit(NorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "nor";
	}

	public String visit(NandNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "nand";
	}

	public String visit(IffNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "iff";
	}

	public String visit(IfNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "if";
	}

	public String visit(XorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return left + " " + right + " " + "xor";
	}

	public String visit(NotNode formula, Void parameter) {
		String operand  = formula.getOperand().accept(this, null);
		return operand + " " + "not";
	}

	public String visit(VariableNode formula, Void parameter) {
		return formula.getIdentifier();
	}

	public String visit(ConstantNode formula, Void parameter) {
		return formula.getValue() ? "1" : "0";
	}

	public void dispatchVisit(FormulaNode node) {
		result = "";
		result = node.accept(this, null);
	} 

	public String getResult() {
		return result;
	}
}
