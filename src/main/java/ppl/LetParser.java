package ppl;

import ppl.LetScanner.Token;

import java.util.ArrayList;

public class LetParser {
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private Token lookahead;

	public static void main(String args[]) {
		LetScanner ls = new LetScanner();
		LetParser lp = new LetParser();
		String input = "let x = 7\n" + "in let y = 2\n" + "in let y = let x = -(x, 1)\n" + "in -(x, y)\n" + "in -(-(x, 8), y)";
		ArrayList<Token> tokens1 = new ArrayList<Token>();
		tokens1 = LetScanner.lex(input);
		lp.Parse(tokens1);
	}

	public void Parse(ArrayList<Token> tokens) {
		// make a clone?
		this.tokens = tokens;
		// pop the first Token
		lookahead = this.tokens.remove(0);
		System.out.println("Inside Parse function " + lookahead.toString());
		// begin traversing the grammar
		expression();
		// our list is NOT empty
		if(lookahead != null) {
			// throw an exception
			System.out.println("Invalid Token Input");
		} else {
			System.out.println("Finished");
		}
	}

	public void expression() {
		System.out.println("Inside expression function " + lookahead.toString());
		// expression -> number
		if(lookahead.toString() == "INTEGER") {
			number();
		}
		// expression -> diff-expression
		else if(lookahead.toString().compareTo("MINUS") == 0) {
			diffExp();
		}
		// expression -> isZero
		else if(lookahead.toString().compareTo("ISZERO") == 0) {
			isZero();
		}
		// expression -> if-expression
		else if(lookahead.toString().compareTo("IF") == 0) {
			ifExp();
		}
		// expression -> identifier
		else if(lookahead.toString().compareTo("IDENTIFIER") == 0) {
			identifier();
		}
		// expression -> let-expression
		else if(lookahead.toString().compareTo("(Letexp let)") == 0) {
			letExp();
		}
	}

	public void number() {
		// this is a terminal
		System.out.println("Inside of number function " + lookahead.toString());
	}

	public void diffExp() {
		nextToken();
		System.out.println("Inside of diff function " + lookahead.toString());
		if(lookahead.toString().compareTo("LPAREN") == 0) {
			nextToken();
			// add the expression
			expression();
			diffExp();
		} else if(lookahead.toString().compareTo("COMMA") == 0) {
			nextToken();
			// add the expression
			expression();
			diffExp();
		} else if(lookahead.toString().compareTo("RPAREN") == 0) {
			nextToken();
			// finished
		}
	}

	public void identifier() {
		// this is a terminal
		System.out.println("Inside of identifier function " + lookahead);
	}

	private void nextToken() {
		// if we reach end of list
		if(tokens.isEmpty()) lookahead = null;
			// otherwise pop the next Token
		else lookahead = this.tokens.remove(0);
	}

	private void isZero() {
		nextToken();
		System.out.println("Inside of isZero function " + lookahead.toString());
		if(lookahead.toString().compareTo("LPAREN") == 0) {
			nextToken();
			// add the expression
			expression();
			isZero();
		} else if(lookahead.toString().compareTo("RPAREN") == 0) {
			nextToken();
			// finished
		}
	}

	private void ifExp() {
		nextToken();
		System.out.println("Inside of if function " + lookahead.toString());
		expression();
	}

	private void letExp() {
		nextToken();
		System.out.println("Inside of let function" + lookahead);
	}
}
