package at.skagen.apps.sat.ui.cli.factories;

import at.skagen.apps.sat.formula.evaluation.BooleanEvaluation;
import at.skagen.apps.sat.formula.evaluation.FormalEvaluation;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.parser.FormulaParser;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.parser.interpretation.Interpretation;
import at.skagen.apps.sat.parser.interpretation.InterpretationParser;
import at.skagen.apps.sat.ui.cli.printable.PrintableBooleanEvaluation;
import at.skagen.apps.sat.ui.cli.printable.PrintableEvaluation;
import at.skagen.apps.sat.ui.cli.printable.PrintableFormalEvaluation;

public class BooleanEvaluationFactory implements EvaluationFactory {

	public PrintableEvaluation create(String[] args) throws FactoryException {
		
		PrintableEvaluation evaluation = null;
		
		FormulaNode formula = null;
		Interpretation interpretation = null;
		
		if (args.length == 3 && "formal".equals(args[0])) {
			formula = readFormula(args, 1);
			interpretation = readInterpretation(args, 2);
			evaluation = new PrintableFormalEvaluation(formula, new FormalEvaluation(formula, interpretation));
		} else if (args.length == 2) {
			formula = readFormula(args, 0);
			interpretation = readInterpretation(args, 1);
			evaluation = new PrintableBooleanEvaluation(formula, new BooleanEvaluation(formula, interpretation));
		}
	
		return evaluation;
	}

	private FormulaNode readFormula(String[] args, int index) throws FactoryException {
		if (index >= args.length) {
			throw new FactoryException("Invalid parameters : expected formula");
		}
		FormulaNode formula = null;
		try {
			formula = new FormulaParser().parse(args[index]);
		} catch (ParserException e) {
			throw new FactoryException(e.getMessage());
		}
		
		return formula;
	}
	
	private Interpretation readInterpretation(String[] args, int index) throws FactoryException {
		if (index >= args.length) {
			throw new FactoryException("Invalid parameters : expected interpretation");
		}
		Interpretation interpretation = null;
		try {
			interpretation = new InterpretationParser().parse(args[index]);
		} catch (ParserException e) {
			throw new FactoryException(e.getMessage());
		}
		
		return interpretation;
	}
}
