package at.skagen.apps.sat.formula.evaluation.formal;

import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.BinaryNode;
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
import at.skagen.apps.sat.formula.printer.InfixPrinter;

public class FormalEvaluator implements Visitor<String, Void> {
	
	private String result;
	
	public void dispatchVisit(FormulaNode node) {
		result = null;
		result = node.accept(this, null);
	}

	public String visit(AndNode formula, Void parameter) {
		return visitBinary(formula, "AND");
	}

	public String visit(OrNode formula, Void parameter) {
		return visitBinary(formula, "OR");
	}

	public String visit(NorNode formula, Void parameter) {
		return visitBinary(formula, "NOR");
	}

	public String visit(NandNode formula, Void parameter) {
		return visitBinary(formula, "NAND");
	}

	public String visit(IffNode formula, Void parameter) {
		return visitBinary(formula, "IFF");
	}

	public String visit(IfNode formula, Void parameter) {
		return visitBinary(formula, "IF");
	}

	public String visit(XorNode formula, Void parameter) {
		return visitBinary(formula, "XOR");
	}

	public String visit(NotNode formula, Void parameter) {
		InfixPrinter printer = new InfixPrinter();
		printer.dispatchVisit(formula.getOperand());
		return "NOT val(I," + printer.getResult() + ")"; 
	}

	public String visit(VariableNode formula, Void parameter) {
		return "I(" + formula.getIdentifier() + ")";
	}

	public String visit(ConstantNode formula, Void parameter) {
		return formula.getValue() ? "1" : "0";
	}
	
	private String visitBinary(BinaryNode formula, String operator) {
		InfixPrinter printer = new InfixPrinter();
		printer.dispatchVisit(formula.getLeftChild());
		String left  = printer.getResult();
		printer.dispatchVisit(formula.getRightChild());
		String right = printer.getResult();
		return "val(I," + left + ") " + operator + " val(I," + right + ")"; 
	}
	
	public String getResult() {
		return result;
	}
}
