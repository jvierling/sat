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

public class InfixPrinter implements Visitor<Void, Void> {

	private String result = "";
	
	public Void visit(AndNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " and " + right + ")";
		
		return null;
	}

	public Void visit(OrNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " or " + right + ")";
		
		return null;
	}

	public Void visit(NandNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " nand " + right + ")";
		
		return null;
	}

	public Void visit(NorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " nor " + right + ")";
		
		return null;
	}

	public Void visit(IffNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " iff " + right + ")";
		
		return null;
	}

	public Void visit(XorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " xor " + right + ")";
		
		return null;
	}

	public Void visit(IfNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "(" + left + " if " + right + ")";
		
		return null;
	}

	public Void visit(NotNode formula, Void p) {
		
		formula.getOperand().accept(this);
		String operand = result;
		
		result = "not " + operand;
		
		return null;
	}

	public Void visit(VariableNode formula, Void p) {
		result = formula.getIdentifier();
		
		return null;
	}

	public Void visit(ConstantNode formula, Void p) {
		result = (formula.getValue()) ? "1" : "0";
		
		return null;
	}

	public String getResult() {
		return result;
	}

	public void dispatchVisit(FormulaNode node) {
		node.accept(this);
	}
}
