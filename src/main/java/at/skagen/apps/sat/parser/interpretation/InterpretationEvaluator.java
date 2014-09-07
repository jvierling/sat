package at.skagen.apps.sat.parser.interpretation;

import java.util.HashMap;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.parser.ParserException;
import static at.skagen.apps.sat.parser.interpretation.Symbols.*;

public class InterpretationEvaluator {

	public HashMap<String, Boolean> evaluate(String interpretation) throws EvaluatorException {
		
		HashMap<String, Boolean> variableTable = new HashMap<String, Boolean>();
		
		try {
			ParseTree parseResult = new InterpretationParser().parse(interpretation);
			
			for (VariableInterpretationNode node : parseResult.getRoot().getChildren()) {
				if (variableTable.get(node.getIdentifier()) != null) {
					throw new EvaluatorException("multiple occurrences of variable " + node.getIdentifier());
				}
				
				boolean value = node.getValue() == Verum ? true : false;
				
				variableTable.put(node.getIdentifier(), value);
			}
		} catch (ParserException e) {
			throw new EvaluatorException(e.getMessage());
		}
		
		return variableTable;
	}
}
