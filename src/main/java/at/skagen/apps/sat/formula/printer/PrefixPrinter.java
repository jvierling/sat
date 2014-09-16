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

public class PrefixPrinter implements Visitor<String, Void> {

	private String result = "";
	
	public String visit(AndNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "and" + " " + left + " " + right;
	}

	public String visit(OrNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "or" + " " + left + " " + right;
	}

	public String visit(NorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "nor" + " " + left + " " + right;
	}

	public String visit(NandNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "nand" + " " + left + " " + right;
	}

	public String visit(IffNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "iff" + " " + left + " " + right;
	}

	public String visit(IfNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "if" + " " + left + " " + right;
	}

	public String visit(XorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "xor" + " " + left + " " + right;
	}

	public String visit(NotNode formula, Void parameter) {
		String operand = formula.getOperand().accept(this, null);
		return "not" + " " + operand;
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
