package at.skagen.apps.sat.formula.evaluation.tableau;

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

public class ContradictionChecker implements Visitor<Void, Void> {

	private boolean result;
	
	private boolean value;
	
	public ContradictionChecker(boolean value) {
		this.value = value;
	}
	
	public Void visit(AndNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(OrNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(NorNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(NandNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(IffNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(IfNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(XorNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(NotNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(VariableNode formula, Void parameter) {
		result = false;
		return null;
	}

	public Void visit(ConstantNode formula, Void parameter) {
		result = formula.getValue() != value;
		return null;
	}

	public void dispatchVisit(FormulaNode node) {
		result = false;
		node.accept(this, null);
	}

	public boolean getResult() {
		return result;
	}
}
