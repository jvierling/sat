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

public class FunctionalPrinter implements Visitor<Void, Void> {

	String result = "";
	
	public Void visit(AndNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "and(" + left + ", " + right + ")";
		
		return null;
	}
	
	public Void visit(OrNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "or(" + left + ", " + right + ")";
		
		return null;
	}

	public Void visit(NandNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "nand(" + left + ", " + right + ")";
		
		return null;
		
	}

	public Void visit(NorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "nor(" + left + ", " + right + ")";
		
		return null;
	}

	public Void visit(IffNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "iff(" + left + ", " + right + ")";
		
		return null;
	}

	public Void visit(XorNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "xor(" + left + ", " + right + ")";
		
		return null;
	}

	public Void visit(IfNode formula, Void p) {
		
		formula.getLeftChild().accept(this);
		String left = result;
		
		formula.getRightChild().accept(this);
		String right = result;
		
		result = "if(" + left + ", " + right + ")";
		
		return null;
	}

	public Void visit(NotNode formula, Void p) {
		
		formula.getOperand().accept(this);
		String left = result;
		
		result = "not(" + left + ")";
		
		return null;
	}

	public Void visit(VariableNode formula, Void p) {
		result = formula.getIdentifier();
		return null;
	}

	public Void visit(ConstantNode formula, Void p) {
		result = formula.getValue() ? "0" : "1";
		return null;
	}
	
	public String getResult() {
		return result;
	}

	public void dispatchVisit(FormulaNode node) {
		result = null;
		node.accept(this, null); 
	}
}
