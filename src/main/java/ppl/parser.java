package ppl;

import ppl.LetScanner.Token;

import java.util.ArrayList;

public class parser {
	private static final String Continue = null;
	public static ArrayList<Token> tokens1 = new ArrayList<Token>();
	public static ArrayList<String> tokens2 = new ArrayList<String>();
	public static String Token;
	public static int index = 0;

	public static void main(String args[]) {
		// LetScanner ls = new LetScanner();
		// String input = "let x = 7\n" + "in let y = 2\n" + "in let y = let x = -(x,
		// 1)\n" + "in -(x, y)\n"
		// + "in -(-(x, 8), y)";
		String input = "if" + "iszero(-(x, 11))\n" + "then -(y, 2)\n" + "else -(y, 4)";
		// ArrayList<Token> tokens1 = new ArrayList<Token>();
		tokens1 = LetScanner.lex(input);
		// lp.Parse(tokens1);
		parser p = new parser();
		// pop the first Token
		Token = parser.tokens1.remove(0).toString().split(" ")[0];
		// System.out.println("Inside Parse function " + Token);
		expression();
	}

	public static int expression() {
		while(Token.length() != 0) {
			switch(Token) {
				case "Integeri32": {
					number();
					return 1;
				}
				case "Minus": {
					diffexp();
					return 1;
				}
				case "IsZero": {
					iszero();
					return 1;
				}
				case "Ifexp": {
					ifexp();
					return 1;
				}
				case "Identifier": {
					identifier();
					return 1;
				}
				case "Letexp": {
					letexp();
					return 1;
				}
				default: {
					incrementToken();
					return 1;
				}
			}
		}
		return 1;
	}

	public static void number() {
		System.out.println("Integeri32 " + Token);
		tokens2.add(Token);
		incrementToken();
	}

	public static void diffexp() {
		System.out.println("diff" + Token);
		tokens2.add(Token);
		incrementToken();
		Lpran();
		expression();
		Comma();
		expression();
		Rpran();
	}

	public static void Lpran() {
		if(Token.equalsIgnoreCase("LPARN")) {
			System.out.println("Lpranthesis");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.print("Error");
		}
	}

	public static void Comma() {
		if(Token.equalsIgnoreCase("Comma")) {
			System.out.println("Comma");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void Rpran() {
		if(Token.equalsIgnoreCase("Rparen")) {
			System.out.println("Rpranthesis");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void assign() {
		if(Token.equalsIgnoreCase("Assign")) {
			System.out.println("Assign");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void in() {
		if(Token.equalsIgnoreCase("Inexp")) {
			System.out.println("in");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void iszero() {
		System.out.println("iszero" + Token);
		tokens2.add(Token);
		incrementToken();
		Lpran();
		expression();
		Rpran();
	}

	public static void els() {
		if(Token.equalsIgnoreCase("Then")) {
			System.out.println("else");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("error");
		}
	}

	public static void then() {
		if(Token.equalsIgnoreCase("Then")) {
			System.out.println("then");
			tokens2.add(Token);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void identifier() {
		System.out.println("identifier  " + Token);
		tokens2.add(Token);
		incrementToken();
	}

	public static void letexp() {
		System.out.println("let " + Token);
		tokens2.add(Token);
		incrementToken();
		identifier();
		assign();
		expression();
		in();
		expression();
	}

	public static void ifexp() {
		System.out.println("if" + Token);
		tokens2.add(Token);
		incrementToken();
		expression();
		then();
		expression();
		els();
		expression();
	}

	public static void incrementToken() {
		if(!(index >= tokens1.size())) {
			Token = tokens1.get(index).toString().split(" ")[0];
			index = index + 1;
		}
	}

	public ArrayList<String> returnParser() {
		String input1 = "let x = 7\n" + "in let y = 2\n" + "in let y = let x = -(x, 1)\n" + "in -(x, y)\n" + "in -(-(x, 8), y)";
		tokens1 = LetScanner.lex(input1);
		// lp.Parse(tokens1);
		parser p = new parser();
		// pop the first Token
		Token = parser.tokens1.remove(0).toString().split(" ")[0];
		// System.out.println("Inside Parse function " + Token);
		expression();
		return tokens2;
	}
}
