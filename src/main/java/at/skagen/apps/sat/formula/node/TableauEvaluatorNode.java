package at.skagen.apps.sat.formula.node;

import java.util.List;

import at.skagen.apps.sat.formula.evaluation.TableauEvaluation;
import at.skagen.apps.sat.formula.evaluation.TableauFormula;
import at.skagen.apps.sat.formula.evaluation.TableauNode;

public interface TableauEvaluatorNode {

	public List<TableauNode> evaluateTableau(TableauEvaluation evaluation, boolean value);
	
	public TableauFormula toTableauFormula(TableauEvaluation evaluation, boolean value);
}
