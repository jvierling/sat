package at.skagen.apps.sat.formula.evaluation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import at.skagen.apps.sat.formula.evaluation.tableau.TableauFormula;

public class TableauNode {

	private Set<TableauFormula> formulas = new HashSet<TableauFormula>();
	
	private TableauNode parent = null;
	
	private List<TableauNode> children = new LinkedList<TableauNode>();
	
	public TableauNode(Set<TableauFormula> formulas) {
		this.formulas.addAll(formulas);
	}
	
	public TableauNode(TableauFormula formula) {
		this.formulas.add(formula);
	}
	
	public Set<TableauFormula> getFormulas() {
		return formulas;
	}

	public void addChild(TableauNode node) {
		children.add(node);
		node.parent = this;
	}
	
	public List<TableauNode> getChildren() {
		return children;
	}
	
	public boolean isClosed() {
		return formulasContradictKnownFormula() || allChildrenClosed();
	}
	
	private boolean isClosedBy(TableauFormula formula) {
		
		if (formula.isContradictory()) {
			return true;
		}
		for (TableauFormula otherFormula : formulas) {
			if (formula.isContradictory(otherFormula)) {
				return true;
			}
		}
		if (parent != null && parent.isClosedBy(formula)) {
			return true;
		}
		
		return false;
	}
	
	private boolean formulasContradictKnownFormula() {
		for (TableauFormula formula : formulas) {
			if (isClosedBy(formula)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean allChildrenClosed() {
		for (TableauNode child : children) {
			if (!child.isClosed()) {
				return false;
			}
		}
		
		return false;
	}
}
