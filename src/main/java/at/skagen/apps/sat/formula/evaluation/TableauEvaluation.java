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
		
		TableauNode root = new InnerNode(formula.toTableauFormula(this, true));
		
		expandFormulas(root, new HashSet<TableauFormula>(), new HashSet<TableauFormula>());
		
		return renderSurface(root.renderTableau());
	}
	
	private String renderSurface(char[][] surface) {
		
		String result = "";
		
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[i].length; j++) {
				result += surface[i][j];
			}
			result += "\n";
		}
		
		return result.substring(0, result.length());
	}
	
	public List<TableauNode> evaluateTableau(AndNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new InnerNode(formulas, "and"));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, false));
			
			nodes.add(new InnerNode(formulas1, "and"));
			nodes.add(new InnerNode(formulas2, "and"));
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
			
			nodes.add(new InnerNode(formulas1, "xor"));
			nodes.add(new InnerNode(formulas2, "xor"));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new InnerNode(formulas1, "xor"));
			nodes.add(new InnerNode(formulas2, "xor"));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(NorNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			formulas.add(formula.getLeftChild().toTableauFormula(this, false));
			
			nodes.add(new InnerNode(formulas, "nor"));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getRightChild().toTableauFormula(this, true));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, true));
			
			nodes.add(new InnerNode(formulas1, "nor"));
			nodes.add(new InnerNode(formulas2, "nor"));
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
			
			nodes.add(new InnerNode(formulas1, "nor"));
			nodes.add(new InnerNode(formulas2, "nor"));
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			
			formulas.add(formula.getRightChild().toTableauFormula(this, true));
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			
			nodes.add(new InnerNode(formulas, "nor"));
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
			
			nodes.add(new InnerNode(formulas1, "iff"));
			nodes.add(new InnerNode(formulas2, "iff"));
		} else {
			Set<TableauFormula> formulas1 = new HashSet<TableauFormula>();
			formulas1.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas1.add(formula.getRightChild().toTableauFormula(this, false));
			
			Set<TableauFormula> formulas2 = new HashSet<TableauFormula>();
			formulas2.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas2.add(formula.getRightChild().toTableauFormula(this, true));
			
			nodes.add(new InnerNode(formulas1, "iff"));
			nodes.add(new InnerNode(formulas2, "iff"));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(NotNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		if (value) {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getOperand().toTableauFormula(this, false));
			nodes.add(new InnerNode(formulas, "not"));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getOperand().toTableauFormula(this, true));
			nodes.add(new InnerNode(formulas, "not"));
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
			
			nodes.add(new InnerNode(formulas1, "if"));
			nodes.add(new InnerNode(formulas2, "if"));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getLeftChild().toTableauFormula(this, true));
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			
			nodes.add(new InnerNode(formulas, "not"));
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
			
			nodes.add(new InnerNode(formulas1, "if"));
			nodes.add(new InnerNode(formulas2, "if"));
			
		} else {
			Set<TableauFormula> formulas = new HashSet<TableauFormula>();
			formulas.add(formula.getLeftChild().toTableauFormula(this, false));
			formulas.add(formula.getRightChild().toTableauFormula(this, false));
			
			nodes.add(new InnerNode(formulas, "not"));
		}
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(ConstantNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		nodes.add(new InnerNode(new ConstantFormula(formula, value)));
		
		return nodes;
	}
	
	public List<TableauNode> evaluateTableau(VariableNode formula, boolean value) {
		
		List<TableauNode> nodes = new LinkedList<TableauNode>();
		
		nodes.add(new InnerNode(new VariableFormula(formula, value)));
		
		return nodes;
	}
	
	private void expandFormulas(TableauNode root, Set<TableauFormula> formulas, Set<TableauFormula> unexpanded) {
		
		formulas.addAll(root.getFormulas());
		
		for (TableauFormula formula : root.getFormulas()) {
			if (formula.isExpandable()) {
				unexpanded.add(formula);
			}
		}
		for (TableauFormula formula : root.getFormulas()) {
			if (formula.isClosed(formulas)) {
				root.addChild(new ClosedNode());
				return;
			}
		}
		if (unexpanded.isEmpty()) {
			root.addChild(new UnclosedNode());
			return;
		}
		
		TableauFormula formula = unexpanded.iterator().next();
		unexpanded.remove(formula);
		
		for (TableauNode node : formula.getFormula().evaluateTableau(this, formula.getValue())) {
			root.addChild(node);
			expandFormulas(node, new HashSet<TableauFormula>(formulas), new HashSet<TableauFormula>(unexpanded));
		}
	}
	
//	private void expandFormulas(TableauNode node, Set<TableauFormula> expanded, Set<TableauFormula> unexpanded) {
//		for (TableauFormula formula : node.getFormulas()) {
//			if (formula.isClosed(expanded, unexpanded)) {
//				node.addChild(new ClosedNode());
//				return;
//			}
//			if (!expanded.contains(formula)) {
//				unexpanded.add(formula);
//			}
//		}
//		if (unexpanded.isEmpty()) {
//			node.addChild(new UnclosedNode());
//			return;
//		}
//		TableauFormula formula = unexpanded.iterator().next();
//		node.addChildren(formula.getFormula().evaluateTableau(this, formula.getValue()));
//		unexpanded.remove(formula);
//		expanded.add(formula);
//		for (TableauNode child : node.getChildren()) {
//			expandFormulas(child, new HashSet<TableauFormula>(expanded), new HashSet<TableauFormula>(unexpanded));
//		}
//	}
	
	public TableauFormula toTableauFormula(AndNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(OrNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}

	public TableauFormula toTableauFormula(NorNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(NandNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(IffNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(XorNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(NotNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(ConstantNode node, boolean value) {
		return new ConstantFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(VariableNode node, boolean value) {
		return new VariableFormula(node, value);
	}
	
	public TableauFormula toTableauFormula(IfNode node, boolean value) {
		return new ExpandableFormula(node, value);
	}
	
	public static void main(String[] args) throws EvaluatorException, ParserException {
		System.out.println(new TableauEvaluation("0 iff A").evaluate());
	}
}
