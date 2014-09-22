package at.skagen.apps.sat.ui.cli.factories;

import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.parser.FormulaParser;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.ui.cli.printable.PrintableCnfEvaluation;
import at.skagen.apps.sat.ui.cli.printable.PrintableEvaluation;

public class CnfEvaluationFactory implements EvaluationFactory {

	public PrintableEvaluation create(String[] args) throws FactoryException {
		if (args.length != 1) {
			throw new FactoryException("invalid parameters");
		}
		FormulaNode formula = readFormula(args, 0);
		
		return new PrintableCnfEvaluation(formula);
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
}
