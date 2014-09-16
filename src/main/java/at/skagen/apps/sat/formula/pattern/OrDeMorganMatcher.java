package at.skagen.apps.sat.formula.pattern;

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
import static at.skagen.apps.sat.formula.pattern.Symbols.NOT;
import static at.skagen.apps.sat.formula.pattern.Symbols.OR;
import static at.skagen.apps.sat.formula.pattern.Symbols.FORMULA;

public class OrDeMorganMatcher implements Visitor<Boolean, Pattern<Symbols>> {

	private Pattern<Symbols> pattern = new Pattern<Symbols>(NOT);
	
	private List<FormulaNode> formulas = new LinkedList<FormulaNode>();
	
	private boolean matched = false;
	
	public OrDeMorganMatcher() {
		pattern.addChild(new Pattern<Symbols>(OR));
		pattern.getChildren().get(0).addChild(new Pattern<Symbols>(FORMULA));
		pattern.getChildren().get(0).addChild(new Pattern<Symbols>(FORMULA));
	}

	public FormulaNode getF() {
		return matched ? formulas.get(0) : null;
	}
	
	public FormulaNode getG() {
		return matched ? formulas.get(1) : null;
	}
	
	public Boolean visit(AndNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(OrNode formula, Pattern<Symbols> current) {
		if (current.getValue() == OR) {
			boolean left  = formula.getLeftChild().accept(this, current.getChildren().get(0));
			boolean right = formula.getRightChild().accept(this, current.getChildren().get(1));
			return left && right;
		} else if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(NandNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(NorNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(IffNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(XorNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(IfNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(NotNode formula, Pattern<Symbols> current) {
		if (current.getValue() == NOT) {
			boolean operand  = formula.getOperand().accept(this, current.getChildren().get(0));
			return operand;
		} else if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(VariableNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(ConstantNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}
	
	public boolean hasMatched() {
		return matched;
	}

	public void dispatchVisit(FormulaNode node) {
		formulas.clear();
		matched = false;
		matched = node.accept(this, pattern);
	}
}
