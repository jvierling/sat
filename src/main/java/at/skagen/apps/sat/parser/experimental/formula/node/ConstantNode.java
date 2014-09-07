package at.skagen.apps.sat.parser.experimental.formula.node;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.skagen.apps.parser.experimental.Evaluator;
import at.skagen.apps.parser.experimental.FormalEvaluation;
import at.skagen.apps.parser.experimental.TableauEvaluation;
import at.skagen.apps.parser.experimental.TableauFormula;
import at.skagen.apps.parser.experimental.TableauNode;
import at.skagen.apps.sat.parser.formula.EvaluatorException;

public class ConstantNode extends NullaryNode {

	private boolean value;
	
	public ConstantNode(boolean value) {
		super(value ? "1" : "0");
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}
	
	@Override
	public Set<String> registerSymbols() { return new HashSet<String>(); }

	public boolean evaluateBoolean(Evaluator<?> evaluator, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return evaluator.evaluateBoolean(this, interpretations);
	}

	public String evaluateFormal(FormalEvaluation evaluator, int limit) throws EvaluatorException {
		return evaluator.evaluateFormal(this, limit);
	}
	
	public List<TableauNode> evaluateTableau(TableauEvaluation evaluation, boolean value) {
		return evaluation.evaluateTableau(this, value);
	}
	
	public TableauFormula toTableauFormula(TableauEvaluation evaluation, boolean value) {
		return evaluation.toTableauFormula(this, value);
	}
}
