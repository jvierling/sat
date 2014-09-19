package at.skagen.apps.sat.formula.evaluation;

import java.util.HashSet;
import java.util.Set;

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

public class VariableLister implements Visitor<Void, Void> {

	private Set<String> variables;
	
	public Void visit(AndNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(OrNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(NorNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(NandNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(IffNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(IfNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(XorNode formula, Void parameter) {
		formula.getLeftChild().accept(this);
		formula.getRightChild().accept(this);
		return null;
	}

	public Void visit(NotNode formula, Void parameter) {
		formula.getOperand().accept(this);
		return null;
	}

	public Void visit(VariableNode formula, Void parameter) {
		variables.add(formula.getIdentifier());
		return null;
	}

	public Void visit(ConstantNode formula, Void parameter) {
		return null;
	}

	public void dispatchVisit(FormulaNode node) {
		variables = new HashSet<String>();
		node.accept(this, null);
	}

	public Set<String> getResult() {
		return variables;
	}
}
