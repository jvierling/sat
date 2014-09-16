package at.skagen.apps.sat.formula.evaluation.tableau;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import at.skagen.apps.sat.formula.evaluation.TableauNode;
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

public class TableauEvaluator implements Visitor<Void, Boolean> {

	private List<TableauNode> nodes = null;

	public Void visit(AndNode formula, Boolean value) {
		
		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(new TableauFormula(formula.getLeftChild(), true));
			formulas.add(new TableauFormula(formula.getRightChild(), true));
			
			nodes.add(new TableauNode(formulas));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getRightChild(), false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return null;
	}

	public Void visit(OrNode formula, Boolean value) {
		
		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getLeftChild(), true));
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getRightChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(new TableauFormula(formula.getLeftChild(), false));
			formulas.add(new TableauFormula(formula.getRightChild(), false));
			
			nodes.add(new TableauNode(formulas));
		}
		return null;
	}

	public Void visit(NorNode formula, Boolean value) {
		
		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(new TableauFormula(formula.getRightChild(), false));
			formulas.add(new TableauFormula(formula.getLeftChild(), false));
			
			nodes.add(new TableauNode(formulas));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getRightChild(), true));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return null;
	}

	public Void visit(NandNode formula, Boolean value) {

		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getRightChild(), false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(new TableauFormula(formula.getRightChild(), true));
			formulas.add(new TableauFormula(formula.getLeftChild(), true));
			
			nodes.add(new TableauNode(formulas));
		}
		return null;
	}

	public Void visit(IffNode formula, Boolean value) {

		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getRightChild(), false));
			formulas1.add(new TableauFormula(formula.getLeftChild(), false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getRightChild(), true));
			formulas2.add(new TableauFormula(formula.getLeftChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getLeftChild(), true));
			formulas1.add(new TableauFormula(formula.getRightChild(), false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), false));
			formulas2.add(new TableauFormula(formula.getRightChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		return null;
	}

	public Void visit(IfNode formula, Boolean value) {

		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getLeftChild(), false));
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getRightChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(new TableauFormula(formula.getLeftChild(), true));
			formulas.add(new TableauFormula(formula.getRightChild(), false));
			
			nodes.add(new TableauNode(formulas));
		}
		return null;
	}

	public Void visit(XorNode formula, Boolean value) {
		
		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getLeftChild(), false));
			formulas1.add(new TableauFormula(formula.getRightChild(), true));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), true));
			formulas2.add(new TableauFormula(formula.getRightChild(), false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(new TableauFormula(formula.getLeftChild(), false));
			formulas1.add(new TableauFormula(formula.getRightChild(), false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(new TableauFormula(formula.getLeftChild(), true));
			formulas2.add(new TableauFormula(formula.getRightChild(), true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		return null;
	}

	public Void visit(NotNode formula, Boolean value) {
		
		nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(new TableauFormula(formula.getOperand(), false));
			nodes.add(new TableauNode(formulas));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(new TableauFormula(formula.getOperand(), true));
			nodes.add(new TableauNode(formulas));
		}
		return null;
	}

	public Void visit(VariableNode formula, Boolean value) {
		nodes = new LinkedList<TableauNode>();
		nodes.add(new TableauNode(new TableauFormula(formula, value)));
		return null;
	}

	public Void visit(ConstantNode formula, Boolean value) {
		nodes = new LinkedList<TableauNode>();
		nodes.add(new TableauNode(new TableauFormula(formula, value)));
		return null;
	}
	
	public void dispatchVisit(FormulaNode node) {
		throw new UnsupportedOperationException();
	}

	public TableauEvaluator dispatchVisit(TableauFormula node) {
		nodes = null;
		node.accept(this, node.getValue());
		return this;
	}
	
	public List<TableauNode> getResult() {
		return nodes;
	}
}
