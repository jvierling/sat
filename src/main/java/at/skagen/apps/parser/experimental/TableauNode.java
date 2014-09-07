package at.skagen.apps.parser.experimental;

import java.util.List;
import java.util.Set;

public interface TableauNode {

	public Set<TableauFormula> getFormulas();

	public void addChild(TableauNode node);

	public void addChildren(List<TableauNode> evaluateTableau);
	
	public List<TableauNode> getChildren();
	
	public char[][] renderTableau();
}
