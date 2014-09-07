package at.skagen.apps.sat.parser.formula;

import java.util.Iterator;

import at.skagen.apps.sat.parser.formula.node.AndNode;
import at.skagen.apps.sat.parser.formula.node.ConstantNode;
import at.skagen.apps.sat.parser.formula.node.FormulaNode;
import at.skagen.apps.sat.parser.formula.node.IfNode;
import at.skagen.apps.sat.parser.formula.node.IffNode;
import at.skagen.apps.sat.parser.formula.node.NandNode;
import at.skagen.apps.sat.parser.formula.node.NorNode;
import at.skagen.apps.sat.parser.formula.node.NotNode;
import at.skagen.apps.sat.parser.formula.node.OrNode;
import at.skagen.apps.sat.parser.formula.node.VariableNode;
import at.skagen.apps.sat.parser.formula.node.XorNode;
import static at.skagen.apps.sat.parser.formula.FormulaTokens.*;

public class FormulaParser {

	private Iterator<Token> tokens = null;
	
	private Token currentToken = null;
	
	public ParseTree parse(String formula) throws ParserException {
		
		ParseTree result = null;
		
		try {
			tokens = new FormulaLexer().lex(formula).iterator();
			
			if (!tokens.hasNext()) {
				throw new ParserException("expected formula");
			}
			currentToken = tokens.next();
			
			FormulaNode root = formula();
			
			result = new ParseTree(root);
			
		} catch (LexerException e) {
			throw new ParserException(e.getMessage());
		}
		
		return result;
	}
	
	private boolean accept(FormulaTokens symbol) {
		if (currentToken == null) {
			return false;
		}
		if (symbol.equals(currentToken.getSymbol())) {
			if (tokens.hasNext()) {
				currentToken = tokens.next();
			} else {
				currentToken = null;
			}
			return true;
		}
		return false;
	}
	
	private FormulaNode formula() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = nandConjunct();
		
		if (accept(Nor)) {
			return new NorNode(left, formula());
		}
		
		return left;
	}
	
	private FormulaNode nandConjunct() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = xorDisjunct();
		
		if (accept(Nand)) {
			return new NandNode(left, nandConjunct());
		}
		
		return left;
	}
	
	private FormulaNode xorDisjunct() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = biconditional();
		
		if (accept(Xor)) {
			return new XorNode(left, xorDisjunct());
		}
		
		return left;
	}
	
	private FormulaNode biconditional() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = conditional();
		
		if (accept(Iff)) {
			return new IffNode(left, biconditional());
		}
		
		return left;
	}
	
	private FormulaNode conditional() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = disjunct();
		
		if (accept(If)) {
			return new IfNode(left, conditional());
		}
		
		return left;
	}
	
	private FormulaNode disjunct() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = conjunct();
		
		if (accept(Or)) {
			return new OrNode(left, disjunct());
		}
		
		return left;
	}
	
	private FormulaNode conjunct() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode left = operand();
		
		if (accept(And)) {
			return new AndNode(left, conjunct());
		}
		
		return left;
	}
	
	private FormulaNode operand() throws ParserException {
		
		if (currentToken == null) {
			throw new ParserException("expected token but got nothing");
		}
		
		FormulaNode node = null;
		
		if (accept(Not)) {
			node = new NotNode(operand());
		} else if (accept(Lparen)) {
			node = formula();
			if (!accept(Rparen)) {
				throw new ParserException("expected ')' but got " + currentToken.getContent());
			}
		} else if (currentToken.getSymbol().equals(Variable)) {
			node = new VariableNode(currentToken.getContent());
			accept(Variable);
		} else if (accept(Verum)) {
			node = new ConstantNode(true);
		} else if (accept(Falsum)) {
			node = new ConstantNode(false);
		} else {
			throw new ParserException("expected 'not', variable, '1', '0' but got " + currentToken.getContent());
		}
		
		return node;
	}
}
