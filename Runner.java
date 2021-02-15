package originalcode;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Runner {
	
	public static void main(String args[]) throws FileNotFoundException {
		//retrieve source file
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is the location of the source file?\n"
		+ "For example: /home/jimmygruber/Documents/example.sciFair\n");
		String fileName = scanner.nextLine();  //****UNCOMMENT****
		//File file = new File("/home/jimmygruber/ProgLang/simplelanguage/language/src/main/java/originalcode/examplebetter.SciFair");  //change the parameter to be "fileName" instead of the location of example.txt
		File file = new File(fileName);
		Scanner scan = new Scanner(file);
		
		//construct the input for the lexer
		String input = "";
		while(scan.hasNextLine()) {
			input += scan.nextLine() + " ";
		}
		scan.close();
		scanner.close();
		
		//run the lexer
		Lexer lexer = new Lexer();
		lexer.setInput(input);
		//System.out.println(Lexer.inputList());
		lexer.inputList();
		lexer.makeTokens();
		//lexer.out();

		Parser parser = new Parser();

		parser.setTokenList(lexer.getTokens());

		ArrayList toDo = parser.parse(lexer.getTokens());
		//System.out.println("");

		for(int i = 0; i < toDo.size(); i++){
			//System.out.println(toDo.get(i).getClass());
			if(toDo.get(i).getClass() != IntWrap.class){
				CallTarget epic = Truffle.getRuntime().createCallTarget(new SciFairRootNode((SciFairNode) toDo.get(i)));
				epic.call();
			}
		}

		//System.out.println(((IntWrap)toDo.get(0)).num);
	} 
	
}
	

