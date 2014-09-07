package at.skagen.apps.sat.formula.evaluation;

public abstract class UnexpandableFormula implements TableauFormula {

	public boolean isExpandable() {
		return false;
	}
}
