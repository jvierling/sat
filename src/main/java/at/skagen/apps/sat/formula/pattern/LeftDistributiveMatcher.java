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
import static at.skagen.apps.sat.formula.pattern.Symbols.*;

public class LeftDistributiveMatcher implements Visitor<Boolean, Pattern<Symbols>> {

	private Pattern<Symbols> pattern = new Pattern<Symbols>(AND);
	
	private Pattern<Symbols> pattern2 = new Pattern<Symbols>(AND);
	
	private List<FormulaNode> formulas = new LinkedList<FormulaNode>();
	
	private boolean matched;
	
	public LeftDistributiveMatcher() {
		pattern.addChild(new Pattern<Symbols>(FORMULA));
		pattern.addChild(new Pattern<Symbols>(OR));
		pattern.getChildren().get(1).addChild(new Pattern<Symbols>(FORMULA));
		pattern.getChildren().get(1).addChild(new Pattern<Symbols>(FORMULA));
		
		pattern2.addChild(new Pattern<Symbols>(OR));
		pattern2.addChild(new Pattern<Symbols>(FORMULA));
		pattern2.getChildren().get(0).addChild(new Pattern<Symbols>(FORMULA));
		pattern2.getChildren().get(0).addChild(new Pattern<Symbols>(FORMULA));	
	}
	
	public FormulaNode getF() {
		return matched ? formulas.get(0) : null;
	}
	
	public FormulaNode getG() {
		return matched ? formulas.get(1) : null;
	}
	
	public FormulaNode getH() {
		return matched ? formulas.get(2) : null;
	}
	
	public boolean hasMatched() {
		return matched;
	}
	
	public Boolean visit(OrNode formula, Pattern<Symbols> current) {
		
		FormulaNode left  = formula.getLeftChild();
		FormulaNode right = formula.getRightChild();
		
		if (current.getValue() == OR) {
			boolean leftMatched  = left.<Boolean, Pattern<Symbols>>accept(this, current.getChildren().get(0));
			boolean rightMatched = right.<Boolean, Pattern<Symbols>>accept(this, current.getChildren().get(1));
			return leftMatched && rightMatched;
		} else if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public Boolean visit(AndNode formula, Pattern<Symbols> current) {
		
		FormulaNode left  = formula.getLeftChild();
		FormulaNode right = formula.getRightChild();
		
		if (current.getValue() == AND) {
			boolean leftMatched  = left.<Boolean, Pattern<Symbols>>accept(this, current.getChildren().get(0));
			boolean rightMatched = right.<Boolean, Pattern<Symbols>>accept(this, current.getChildren().get(1));
			return leftMatched && rightMatched;
		} else if (current.getValue() == FORMULA) {
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

	public Boolean visit(NandNode formula, Pattern<Symbols> current) {
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

	public Boolean visit(IfNode formula, Pattern<Symbols> current) {
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
	
	public Boolean visit(NotNode formula, Pattern<Symbols> current) {
		if (current.getValue() == FORMULA) {
			formulas.add(formula);
			return true;
		}
		
		return false;
	}

	public void dispatchVisit(FormulaNode node) {
		formulas.clear();
		matched = false;
		matched = node.accept(this, pattern);
	}
}
