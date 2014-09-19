package at.skagen.apps.sat.formula.printer;

import java.util.List;

import at.skagen.apps.sat.formula.linearizer.AndLinearizer;
import at.skagen.apps.sat.formula.linearizer.OrLinearizer;
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

public class LinearPrinter implements Visitor<String, Void> {

	private final String AND_OPERATOR = "and";
	private final String OR_OPERATOR  = "or";
	
	private String result = "";
	
	public String visit(AndNode formula, Void parameter) {
		
		String result = "";
		
		AndLinearizer linearizer = new AndLinearizer();
		linearizer.dispatchVisit(formula);
		List<FormulaNode> operands = linearizer.getResult();
		
		for (FormulaNode operand : operands) {
			result += operand.accept(this, null) + " " + AND_OPERATOR + " ";
		}
		
		return "(" + result.substring(0, result.length() - (" " + AND_OPERATOR + " ").length()) + ")";
	}

	public String visit(OrNode formula, Void parameter) {
		
		String result = "";
		
		OrLinearizer linearizer = new OrLinearizer();
		linearizer.dispatchVisit(formula);
		List<FormulaNode> operands = linearizer.getResult();
		
		for (FormulaNode operand : operands) {
			result += operand.accept(this, null) + " " + OR_OPERATOR + " ";
		}
		
		return "(" + result.substring(0, result.length() - (" " + OR_OPERATOR + " ").length()) + ")";
	}

	public String visit(NorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "(" + left + " nor " + right + ")";
 	}

	public String visit(NandNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "(" + left + " nand " + right + ")";
	}

	public String visit(IffNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "(" + left + " iff " + right + ")";
	}

	public String visit(IfNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "(" + left + " if " + right + ")";
	}

	public String visit(XorNode formula, Void parameter) {
		String left  = formula.getLeftChild().accept(this, null);
		String right = formula.getRightChild().accept(this, null);
		return "(" + left + " xor " + right + ")";
	}

	public String visit(NotNode formula, Void parameter) {
		String operand  = formula.getOperand().accept(this, null);
		return "not (" + operand + ")";
	}

	public String visit(VariableNode formula, Void parameter) {
		return formula.getIdentifier();
	}

	public String visit(ConstantNode formula, Void parameter) {
		return formula.getValue() ? "1" : "0";
	}

	public void dispatchVisit(FormulaNode node) {
		result = "";
		result = node.accept(this, null);
	}
	
	public String getResult() {
		return result;
	}

	public static void main(String[] args) throws ParserException {
		String[] formulas =  {
				"A and B and C or D or E"
		};
		for (String formula : formulas) {
			LinearPrinter printer = new LinearPrinter();
			printer.dispatchVisit(new FormulaParser().parse(formula));
			System.out.println(printer.getResult());
		}
	}
}
