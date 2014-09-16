package at.skagen.apps.sat.formula.evaluation;

import java.util.HashMap;
import java.util.Map;

import at.skagen.apps.sat.formula.node.AndNode;
import at.skagen.apps.sat.formula.node.ConstantNode;
import at.skagen.apps.sat.formula.node.FormulaNode;
import at.skagen.apps.sat.formula.node.IfNode;
import at.skagen.apps.sat.formula.node.IffNode;
import at.skagen.apps.sat.formula.node.NandNode;
import at.skagen.apps.sat.formula.node.NorNode;
import at.skagen.apps.sat.formula.node.NotNode;
import at.skagen.apps.sat.formula.node.OrNode;
import at.skagen.apps.sat.formula.node.VariableNode;
import at.skagen.apps.sat.formula.node.Visitor;
import at.skagen.apps.sat.formula.node.XorNode;
import at.skagen.apps.sat.formula.parser.FormulaParser;
import at.skagen.apps.sat.formula.parser.ParserException;
import at.skagen.apps.sat.parser.interpretation.Interpretation;

public class BooleanEvaluator implements Visitor<Boolean, Void> {

	private final BooleanEvaluator booleanEvaluator = this;
	
	private Interpretation interpretation;
	
	private boolean result;
	
	public BooleanEvaluator(Interpretation interpretation) {
		this.interpretation = interpretation;
	}
	
	public Boolean visit(AndNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return left && right;
	}

	public Boolean visit(OrNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return left || right;
	}

	public Boolean visit(NorNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return !(left || right);
	}

	public Boolean visit(NandNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return !(left && right);
	}

	public Boolean visit(IffNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return (left && right) || (!left && !right);
	}

	public Boolean visit(IfNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return !left || right;
	}

	public Boolean visit(XorNode formula, Void parameter) {
		boolean left  = formula.getLeftChild().accept(booleanEvaluator, null);
		boolean right = formula.getRightChild().accept(booleanEvaluator, null);
		
		return (!left && right) || (left && !right);
	}

	public Boolean visit(NotNode formula, Void parameter) {
		boolean operand  = formula.getOperand().accept(booleanEvaluator, null);
		
		return !operand;
	}

	public Boolean visit(VariableNode formula, Void parameter) {
		return interpretation.getVariableInterpretation(formula);
	}

	public Boolean visit(ConstantNode formula, Void parameter) {
		return formula.getValue();
	}

	public void dispatchVisit(FormulaNode node) {
		result = node.accept(booleanEvaluator, null);
	}

	public boolean getResult() {
		return result;
	}
	
	public static void main(String[] args) throws ParserException {
		String[] formulas = {
				"1",
				"0",
				"A and B"
		};
		Map<String, Boolean> interpretation = new HashMap<String, Boolean>();
		interpretation.put("A", true);
		interpretation.put("B", true);
		
		for (String formula : formulas) {
			BooleanEvaluator evaluator = new BooleanEvaluator(new Interpretation(interpretation));
			evaluator.dispatchVisit(new FormulaParser().parse(formula));
			System.out.println(evaluator.getResult());
		}
	}
}
