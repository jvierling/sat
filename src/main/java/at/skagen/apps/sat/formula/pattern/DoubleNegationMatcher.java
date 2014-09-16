package at.skagen.apps.sat.formula.pattern;

import static at.skagen.apps.sat.formula.pattern.Symbols.FORMULA;
import static at.skagen.apps.sat.formula.pattern.Symbols.NOT;
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

public class DoubleNegationMatcher implements Visitor<Boolean, Pattern<Symbols>> {

	private Pattern<Symbols> pattern = new Pattern<Symbols>(NOT);
	
	private FormulaNode f = null;
	
	private boolean matched = false;
	
	public DoubleNegationMatcher() {
		pattern.addChild(new Pattern<Symbols>(NOT));
		pattern.getChildren().get(0).addChild(new Pattern<Symbols>(FORMULA));
	}
	
	public Boolean visit(AndNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		
		return false;
	}

	public Boolean visit(OrNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(NorNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(NandNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(IffNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(IfNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(XorNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(NotNode formula, Pattern<Symbols> current) {
		if (current.getValue() == NOT) {
			return formula.getOperand().accept(this, current.getChildren().get(0));
		} else if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(VariableNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public Boolean visit(ConstantNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			f = formula;
			return true;
		}
		return false;
	}

	public void dispatchVisit(FormulaNode node) {
		f = null;
		matched = false;
		matched = node.accept(this, pattern);
	}

	public boolean hasMatched() {
		return matched;
	}
	
	public FormulaNode getF() {
		return matched ? f : null;
	}
}
