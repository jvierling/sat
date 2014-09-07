package at.skagen.apps.sat.parser.formula;

public class Token {

	private FormulaTokens type;
	
	private String content;
	
	public Token(FormulaTokens type, String content) {
		this.type    = type;
		this.content = content;
	}
	
	public boolean isType(FormulaTokens type) {
		return this.type == type;
	}
	
	public String getContent() {
		return content;
	}
	
	public FormulaTokens getSymbol() {
		return type;
	}
}
