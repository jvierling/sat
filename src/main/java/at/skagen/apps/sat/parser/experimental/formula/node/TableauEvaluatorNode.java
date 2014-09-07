package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.List;

import at.skagen.apps.parser.experimental.TableauEvaluation;
import at.skagen.apps.parser.experimental.TableauFormula;
import at.skagen.apps.parser.experimental.TableauNode;

public interface TableauEvaluatorNode {

	public List<TableauNode> evaluateTableau(TableauEvaluation evaluation, boolean value);
	
	public TableauFormula toTableauFormula(TableauEvaluation evaluation, boolean value);
}
