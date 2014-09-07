package at.skagen.apps.parser.experimental;

public abstract class UnexpandableFormula implements TableauFormula {

	public boolean isExpandable() {
		return false;
	}
}
