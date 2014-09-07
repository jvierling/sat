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

public class VariableNode extends NullaryNode {

	private String identifier;
	
	public VariableNode(String identifier) {
		super(identifier);
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public boolean evaluateBoolean(Evaluator<?> evaluator, Map<String, Boolean> interpretations) 
			throws EvaluatorException {
		return evaluator.evaluateBoolean(this, interpretations);
	}

	public String evaluateFormal(FormalEvaluation evaluation, int limit) throws EvaluatorException {
		return evaluation.evaluateFormal(this, limit);
	}
	
	public List<TableauNode> evaluateTableau(TableauEvaluation evaluation, boolean value) {
		return evaluation.evaluateTableau(this, value);
	}

	@Override
	public Set<String> registerSymbols() {
		
		HashSet<String> identifiers = new HashSet<String>();
		identifiers.add(identifier);
		
		return identifiers;
	}
	
	public TableauFormula toTableauFormula(TableauEvaluation evaluation, boolean value) {
		return evaluation.toTableauFormula(this, value);
	}
}
