package at.skagen.apps.sat.parser.interpretation;

public class Token {
	
	private Symbols type;
	
	private String content;
	
	public Token(Symbols type, String content) {
		this.type    = type;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public Symbols getSymbol() {
		return type;
	}
	
	public String toString() {
		return content;
	}
}
