package at.skagen.apps.sat.formula.linearizer;

import java.util.LinkedList;
import java.util.List;

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

public class AndLinearizer implements Visitor<List<FormulaNode>, Void> {

	private List<FormulaNode> result = null;
	
	public List<FormulaNode> visit(AndNode formula, Void parameter) {
		List<FormulaNode> leftFormulas  = formula.getLeftChild().accept(this, null);
		List<FormulaNode> rightFormulas = formula.getRightChild().accept(this, null);
		leftFormulas.addAll(rightFormulas);
		return leftFormulas;
	}

	public List<FormulaNode> visit(OrNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(NorNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(NandNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(IffNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(IfNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(XorNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(NotNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(VariableNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public List<FormulaNode> visit(ConstantNode formula, Void parameter) {
		List<FormulaNode> result = new LinkedList<FormulaNode>();
		result.add(formula);
		return result;
	}

	public void dispatchVisit(FormulaNode node) {
		result = null;
		result = node.accept(this, null);
	}
	
	public List<FormulaNode> getResult() {
		return result;
	}
}
