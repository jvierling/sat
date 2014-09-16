package at.skagen.apps.sat.formula.evaluation.tableau;

import java.util.HashSet;
import java.util.Set;

import at.skagen.apps.sat.formula.evaluation.EvaluatorException;
import at.skagen.apps.sat.formula.evaluation.TableauNode;
import at.skagen.apps.sat.formula.evaluation.UninterpretedEvaluation;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.ui.cli.TableauRenderer;

public class TableauEvaluation implements UninterpretedEvaluation<String> {

	private FormulaNode formula;
	
	public TableauEvaluation(FormulaNode formula) throws ParserException {
		this.formula = formula;
	}
	
	/**
	 * Evaluates a formulas satisfiability using tableau calculus.
	 */
	public String evaluate() throws EvaluatorException {
		
		TableauNode root = new TableauNode(new TableauFormula(formula, true));
		
		expandFormulas(root, new HashSet<TableauFormula>());
		
		return new TableauRenderer().renderTableau(root);
	}
	
	private void expandFormulas(TableauNode root, Set<TableauFormula> unexpanded) {
		
		TableauEvaluator evaluator = new TableauEvaluator();
		
		for (TableauFormula formula : root.getFormulas()) {
			if (!formula.isAtomic()) {
				unexpanded.add(formula);
			}
		}

		if (root.isClosed()) {
			return;
		}
		if (unexpanded.isEmpty()) {
			return;
		}
		
		TableauFormula formula = unexpanded.iterator().next();
		unexpanded.remove(formula);
		
		for (TableauNode node : evaluator.dispatchVisit(formula).getResult()) {
			root.addChild(node);
			expandFormulas(node, new HashSet<TableauFormula>(unexpanded));
		}
	}
}
