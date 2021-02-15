package originalcode;

public class Token {
	
	private String type = "";
	private String lexem = "";
	
	public Token(String type, String lexem) {
		this.type = type;
		this.lexem = lexem;
	}
	
	public String getType() {
		return type;
	}
	
	public String getLexem() {
		return lexem;
	}
	
	public void setType(String a) {
		type = a;
	}
	
	public void setLexem(String a) {
		lexem = a;
	}
	
	public String toString() {
		return ("{"+type+"::"+lexem+"}");
	}
}
