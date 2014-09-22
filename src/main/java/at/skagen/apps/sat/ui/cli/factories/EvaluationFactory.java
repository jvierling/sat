package at.skagen.apps.sat.ui.cli.factories;

import at.skagen.apps.sat.ui.cli.printable.PrintableEvaluation;

public interface EvaluationFactory {

	public PrintableEvaluation create(String[] args) throws FactoryException;
}
