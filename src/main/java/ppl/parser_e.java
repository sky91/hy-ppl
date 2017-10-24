package ppl;

import ppl.LetLangExp.ConstExp;
import ppl.LetLangExp.DiffExp;
import ppl.LetLangExp.LetExp;
import ppl.LetLangExp.VarExp;
import ppl.LetScanner.Token;

import java.util.ArrayList;

public class parser_e {
	private static final String Continue = null;
	public static ArrayList<Token> tokens1 = new ArrayList<Token>();
	public static ArrayList<String> tokens2 = new ArrayList<String>();
	public static ArrayList<Token> tokens3 = new ArrayList<Token>();
	public static ArrayList<Token> tokens4 = new ArrayList<Token>();
	//public static LetLangExp lC_exp =new LetLangExp();
	public static Token Token;
	public static int index = 0;

	public static void main(String args[]) {
		// LetScanner ls = new LetScanner();
		String input = "let x = 7 \n" + "in y";
		// String input ="if"+"iszero(-(x, 11))\n" + "then -(y, 2)\n" + "else -(y, 4)";
		// ArrayList<Token> tokens1 = new ArrayList<Token>();
		tokens1 = LetScanner.lex(input);
		tokens3 = LetScanner.lex(input);
		for(Token token : tokens1) {
			System.out.println(token);
		}
		// lp.Parse(tokens1);
		parser_e p = new parser_e();
		// pop the first Token
		Token = parser_e.tokens1.remove(0);
		// Tokens =parser_e.tokens1.remove(0).toString();
		// System.out.println("Inside Parse function " + Token);
		expression();
	}

	public static LetLangExp returnParser(String input) {
		tokens1 = LetScanner.lex(input);
		// lp.Parse(tokens1);
		parser_e p = new parser_e();
		// pop the first Token
		Token = parser_e.tokens1.remove(0);
		// System.out.println("Inside Parse function " + Token);
		//
		return expression();
	}

	public static LetLangExp expression() {
		while(!Token.data.isEmpty()) {
			switch(Token.type) {
				case Integeri32: {
					return number();
					//return tokens2;
				}
				case Minus: {
					return Diffex();
					//return tokens2;
				}
				case Identifier: {
					return identifier();
					//return tokens2;
				}
				case Letexp: {
					return LetExp12();
					//return tokens2;
				}
				default: {
					incrementToken();
					return null;
				}
			}
		}
		return null;
	}

	public static LetLangExp number() {
		System.out.println("Integeri32 " + Token.data);
		tokens2.add(Token.data);
		int num = Integer.parseInt(Token.data);
		incrementToken();
		LetLangExp l3 = new LetLangExp();
		ConstExp c = l3.new ConstExp((num));
		return c;
	}

	public static LetLangExp Diffex() {
		System.out.println("diff" + Token.data);
		tokens2.add(Token.data);
		incrementToken();
		Lpran();
		LetLangExp l1 = new LetLangExp();
		LetLangExp l2 = new LetLangExp();
		LetLangExp l3 = new LetLangExp();
		l1 = expression();
		// ArrayList exp1=expression();
		Comma();
		l2 = expression();
		// ArrayList exp2=expression();
		Rpran();
		DiffExp d = l3.new DiffExp(l1, l2);
		return d;
	}

	public static void Lpran() {
		if(Token.type.equals(LetScanner.TokenType.LPARN)) {
			System.out.println("Lpranthesis");
			//tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.print("Error");
		}
	}

	public static void Comma() {
		if(Token.type.equals(LetScanner.TokenType.Comma)) {
			System.out.println("Comma");
			//tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void Rpran() {
		if(Token.type.equals(LetScanner.TokenType.Rparen)) {
			System.out.println("Rpranthesis");
			//tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void assign() {
		if(Token.type.equals(LetScanner.TokenType.Assign)) {
			System.out.println(Token.data);
			//tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void in() {
		if(Token.type.equals(LetScanner.TokenType.Inexp)) {
			System.out.println("in");
			//tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void then() {
		if(Token.data.equalsIgnoreCase("Then")) {
			System.out.println("then");
			tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("Error");
		}
	}

	public static void els() {
		if(Token.data.equalsIgnoreCase("Then")) {
			System.out.println("else");
			tokens2.add(Token.data);
			incrementToken();
		} else {
			System.out.println("error");
		}
	}

	public static void iszero() {
		System.out.println("iszero" + Token.data);
		tokens2.add(Token.data);
		Lpran();
		expression();
		Rpran();
	}

	public static void ifexp() {
		System.out.println("if" + Token.data);
		tokens2.add(Token.data);
		incrementToken();
		LetLangExp l1 = new LetLangExp();
		LetLangExp l2 = new LetLangExp();
		LetLangExp l3 = new LetLangExp();
		l1 = expression();
		then();
		l2 = expression();
		els();
		l3 = expression();
	}

	public static LetLangExp identifier() {
		System.out.println("Identifier " + Token.data);
		tokens2.add(Token.data);
		String Var = Token.data;
		incrementToken();
		LetLangExp l3 = new LetLangExp();
		VarExp v = l3.new VarExp(Var);
		return v;
	}

	public static LetLangExp LetExp12() {
		System.out.println("let " + Token.data);
		tokens2.add(Token.data);
		incrementToken();
		LetLangExp Ide = identifier();
		assign();
		LetLangExp l1 = new LetLangExp();
		LetLangExp l2 = new LetLangExp();
		LetLangExp l3 = new LetLangExp();
		// ArrayList exp1=expression();
		l1 = expression();
		in();
		// ArrayList exp2=expression();
		l2 = expression();
		LetExp c = l3.new LetExp(Ide, l1, l2);
		return c;
	}

	public static void incrementToken() {
		if(!(index >= tokens1.size())) {
			Token = tokens1.get(index);
			// tokens3.remove(0);
			index = index + 1;
		}
	}
}
