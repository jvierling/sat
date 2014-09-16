package at.skagen.apps.sat.formula.transformers;

import java.util.LinkedList;
import java.util.List;

import at.skagen.apps.sat.formula.node.FormulaNode;

public class DnfSyntaxTransformer {
	
	public List<FormulaNode> transform(FormulaNode formula) {
		
		List<FormulaNode> steps = new LinkedList<FormulaNode>();
		
		steps.add(formula);
		
		FormulaNode step = replaceConnectives(formula);
		if (!step.equals(formula)) {
			steps.add(step);
		}
		do {
			formula = step;
			step = applyDeMorgan(formula);
			if (!step.equals(formula)) {
				steps.add(step);
			}
			formula = step;
			step = elimineDoubleNegation(formula);
			if (!step.equals(formula)) {
				steps.add(step);
			}
		} while (!formula.equals(step));
		do {
			formula = step;
			step = applyDistributiveLaw(formula);
			if (!step.equals(formula)) {
				steps.add(step);
			}
		} while (!formula.equals(step));
		
		// elimine useless conjunctives, disjunctives
		
		return steps;
	}
	
	public FormulaNode elimineDoubleNegation(FormulaNode formula) {
		DoubleNegationEliminer negationEliminer = new DoubleNegationEliminer();
		negationEliminer.dispatchVisit(formula);
		return negationEliminer.getResult();
	}
	
	public FormulaNode replaceConnectives(FormulaNode formula) {
		ConnectiveReplacerVisitor connectiveReplacer = new ConnectiveReplacerVisitor();
		connectiveReplacer.dispatchVisit(formula);
		return connectiveReplacer.getResult();
	}
	
	public FormulaNode applyDeMorgan(FormulaNode formula) {
		DeMorganReplacer deMorganReplacer = new DeMorganReplacer();
		deMorganReplacer.dispatchVisit(formula);
		return deMorganReplacer.getResult();
	}
	
	public FormulaNode applyDistributiveLaw(FormulaNode formula) {
		DistributiveReplacer distributiveReplacer = new DistributiveReplacer();
		distributiveReplacer.dispatchVisit(formula);
		return distributiveReplacer.getResult();
	}
}
