package at.skagen.apps.sat.formula.evaluation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import at.skagen.apps.sat.formula.node.XorNode;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.ui.cli.TableauRenderer;

public class TableauEvaluation extends UninterpretedEvaluation<String> {

	private FormulaNode formula;
	
	public TableauEvaluation(String formula) throws ParserException {
		this.formula = new FormulaParser().parse(formula).getRoot();
	}
	
	/**
	 * Evaluates a formulas satisfiability using tableau calculus.
	 */
	@Override
	public String evaluate() throws EvaluatorException {
		
		TableauNode root = new TableauNode(formula.toTableauFormula(this, false));
		
		expandFormulas(root, new HashSet<TableauFormula>());
		
		return new TableauRenderer().renderTableau(root);
	}
	
	public List<TableauNode> evaluateTableau(AndNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return nodes;
	}

	public List<TableauNode> evaluateTableau(XorNode formula, boolean value) {

		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas1.add(formula.getRightChild().toTableauFormula(this, true));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas2.add(formula.getRightChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(NorNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			formulas.add(formula.getLeftChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, true));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(NandNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getRightChild().toTableauFormula(this, true));
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(IffNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			formulas1.add(formula.getLeftChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(NotNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getOperand().toTableauFormula(this, false));
			nodes.add(new TableauNode(formulas));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getOperand().toTableauFormula(this, true));
			nodes.add(new TableauNode(formulas));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(IfNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, false));
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(OrNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, true));
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new TableauNode(formulas1));
			nodes.add(new TableauNode(formulas2));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			
			nodes.add(new TableauNode(formulas));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(ConstantNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		nodes.add(new TableauNode(new ConstantFormula(formula, value)));
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(VariableNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		nodes.add(new TableauNode(new VariableFormula(formula, value)));
		
		return nodes;
	}
	
	private void expandFormulas(TableauNode root, Set<TableauFormula> unexpanded) {
		for (TableauFormula formula : root.getFormulas()) {
			if (!formula.isAtomic()) {
				unexpanded.add(formula);
			}
		}

		if (root.isClosed()) {
			return;
		}
		if (unexpanded.isEmpty()) {
			return;
		}
		
		TableauFormula formula = unexpanded.iterator().next();
		unexpanded.remove(formula);
		
		for (TableauNode node : formula.getFormula().evaluateTableau(this, formula.getValue())) {
			root.addChild(node);
			expandFormulas(node, new HashSet<TableauFormula>(unexpanded));
		}
	}
	
	public TableauFormula toTableauFormula(AndNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(OrNode node, boolean value) {
		return new ComplexFormula(node, value);
	}

	public TableauFormula toTableauFormula(NorNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(NandNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(IffNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(XorNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(NotNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(ConstantNode node, boolean value) {
		return new ConstantFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(VariableNode node, boolean value) {
		return new VariableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(IfNode node, boolean value) {
		return new ComplexFormula(node, value);
	}
	
	public static void main(String[] args) throws EvaluatorException, ParserException {
		System.out.println(new TableauEvaluation("A or A or A or A or A or A or A or A or A or A").evaluate());
	}
}
