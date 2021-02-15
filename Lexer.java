package originalcode;

import java.util.ArrayList;

public class Lexer {
	
	private String input = "";
	private ArrayList<Character> charList = new ArrayList<Character>();
	private ArrayList<Token> tokenList = new ArrayList<Token>();
	
	public void setInput(String in) {
		input = in;
	}
	
	public String getInput() {
		return input;
	}
	
	public ArrayList<Character> inputList() {
		char[] a = input.toCharArray();
		for(int i = 0; i < a.length; i++) {
			charList.add(a[i]);
		}
		charList.remove(charList.size()-1);
		return charList;
	}
	
	
	//types of tokens: punctuation, keywords, identifiers (sort of implemented), literals (sort of implemented), operators, reserved words <-- still figuring this out 
	public void makeTokens() {
		
		boolean quoteCheck = true;
		boolean numCheck = true;
		boolean alphaCheck1 = true;
		boolean alphaCheck2 = true;
		
		ArrayList<Character> alpha = new ArrayList<Character>();
		char[] temp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(int i = 0; i < temp.length; i++) {
			alpha.add(temp[i]);
		}
		
		for(int i = 0; i < charList.size(); i++) {
			alphaCheck1 = true;
			
			if(charList.get(i)=='[') {
				tokenList.add(new Token("punc","["));
				alphaCheck1 = false;
			}
			if(charList.get(i)==']') {
				tokenList.add(new Token("punc","]"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='(') {
				tokenList.add(new Token("punc","("));
				alphaCheck1 = false;
			}
			if(charList.get(i)==')') {
				tokenList.add(new Token("punc",")"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='|') {
				tokenList.add(new Token("punc","|"));
				alphaCheck1 = false;
			}
			if(i+1<charList.size() && charList.get(i)=='i' && charList.get(i+1)=='f') {
				tokenList.add(new Token("key","if"));
				alphaCheck1 = false;
			}
			if(i+3<charList.size() && charList.get(i)=='e' && charList.get(i+1)=='l' && charList.get(i+2)=='s' && charList.get(i+3)=='e') {
				tokenList.add(new Token("key","else"));
				alphaCheck1 = false;
			}
			if(i+4<charList.size() && charList.get(i)=='w' && charList.get(i+1)=='h' && charList.get(i+2)=='i' && charList.get(i+3)=='l' && charList.get(i+4)=='e') {
				tokenList.add(new Token("key","while"));
				alphaCheck1 = false;
			}
			if(i+4<charList.size() && charList.get(i)=='p' && charList.get(i+1)=='r' && charList.get(i+2)=='i' && charList.get(i+3)=='n' && charList.get(i+4)=='t') {
				tokenList.add(new Token("key","print"));
				alphaCheck1 = false;
			}
			if(i+4<charList.size() && charList.get(i)=='s' && charList.get(i+1)=='t' && charList.get(i+2)=='r' && charList.get(i+3)=='i' && charList.get(i+4)=='n' && charList.get(i+5)=='g') {
				tokenList.add(new Token("res","string"));
				alphaCheck1 = false;
			}
			if(i+4<charList.size() && charList.get(i)=='n' && charList.get(i+1)=='u' && charList.get(i+2)=='m') {
				tokenList.add(new Token("res","num"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='<') {
				tokenList.add(new Token("oper","<"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='>') {
				tokenList.add(new Token("oper",">"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='+') {
				tokenList.add(new Token("oper","+"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='-') {
				tokenList.add(new Token("oper","-"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='*') {
				tokenList.add(new Token("oper","*"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='/') {
				tokenList.add(new Token("oper","/"));
				alphaCheck1 = false;
			}
			if(charList.get(i)=='=') {
				tokenList.add(new Token("oper","="));
				alphaCheck1 = false;
			}
			
			
			if(charList.get(i)=='\"' && quoteCheck) {
				alphaCheck1 = false;
				int j = i+1;
				boolean k = true;
				while(j < charList.size() && k) {
					if(charList.get(j)=='\"') {
						k = false;
					}
					j++;
				}
				String l = "";
				for(int m = i + 1; m < j-1; m++) {
					l += charList.get(m);					
				}
				tokenList.add(new Token("lit",l));
				
				quoteCheck = false;
			} else if(charList.get(i)=='\"' && quoteCheck==false) {
				alphaCheck1 = false;
				quoteCheck = true;
			}
			
			
			if((charList.get(i)=='0' || charList.get(i)=='1' || charList.get(i)=='2' || charList.get(i)=='3' || charList.get(i)=='4' 
					|| charList.get(i)=='5' || charList.get(i)=='6' || charList.get(i)=='7' || charList.get(i)=='8' || charList.get(i)=='9') && quoteCheck && numCheck) {
				alphaCheck1 = false;
				int j = i+1;
				boolean k = true;
				while(j < charList.size() && k) {  
					if(charList.get(j)!='0' && charList.get(j)!='1' && charList.get(j)!='2' && charList.get(j)!='3' && charList.get(j)!='4' 
							&& charList.get(j)!='5' && charList.get(j)!='6' && charList.get(j)!='7' && charList.get(j)!='8' && charList.get(j)!='9') {
						k = false;
					}
					j++;
				}
				String l = "";
				for(int m = i; m < j-1; m++) {
					l += charList.get(m);
				}
				tokenList.add(new Token("lit",l));
				
				numCheck = false;
			} else if(numCheck == false && (charList.get(i-1)=='0' || charList.get(i-1)=='1' || charList.get(i-1)=='2' || charList.get(i-1)=='3' || charList.get(i-1)=='4' 
					|| charList.get(i-1)=='5' || charList.get(i-1)=='6' || charList.get(i-1)=='7' || charList.get(i-1)=='8' || charList.get(i-1)=='9') && !(charList.get(i)=='0' 
					|| charList.get(i)=='1' || charList.get(i)=='2' || charList.get(i)=='3' || charList.get(i)=='4' 
					|| charList.get(i)=='5' || charList.get(i)=='6' || charList.get(i)=='7' || charList.get(i)=='8' || charList.get(i)=='9')) {
				alphaCheck1 = false;
				numCheck = true;
			}
			
			
			if(alpha.contains(charList.get(i)) && i > 0 && !alpha.contains(charList.get(i-1)) && alphaCheck1 && alphaCheck2 && quoteCheck) {
				int j = i + 1;
				boolean k =  true;
				String l = "";
				l += charList.get(i);
				while(j < charList.size() && k) {
					if(!alpha.contains(charList.get(j))){
						k = false;
					} else {
						l += charList.get(j);
					}
					j++;
				}
				tokenList.add(new Token("id",l));
				
				alphaCheck2 = false;
			} else if(!(alpha.contains(charList.get(i))) && alphaCheck1 && alphaCheck2==false) {
				alphaCheck2 = true;
			} 
			
			
		}
	}

	public ArrayList getTokens(){
		return tokenList;
	}

	public void out() {
		
		System.out.print("[");
		for(int i = 0; i < tokenList.size(); i++) {
			System.out.print(tokenList.get(i).toString());
			if(i != tokenList.size()-1)
				System.out.print(", ");
		}
		System.out.print("]");
	}
	
}



