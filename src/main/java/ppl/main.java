package ppl;

import ppl.LetScanner.Token;

import java.util.ArrayList;

public class main {
	public static int index = 0;

	public static void main(String... arg) {
		String input = "let x = 7\n" + "in let y = 2\n" + "in let y = let x = -(x, 1)\n" + "in -(x, y)\n" + "in -(-(x, 8), y)";
		// String input ="if iszero(-(x, 11))\n" + "then -(y, 2)\n" + "else -(y, 4)";
		ArrayList<Token> sclist = new ArrayList<>();
		LetLangExp sclist1 = new LetLangExp();
		LetScanner ls = new LetScanner();
		parser_e p = new parser_e();
		sclist = LetScanner.lex(input);
		for(Token i : sclist) {
			System.out.println(i);
		}
		LetLangExp Lc = parser_e.returnParser(input);
		Environment env = new Environment();
		LetLangExp LLe = new LetLangExp();
		// LLe.tokens1 = sclist1;
		int result = valueOf(Lc, null);
		System.out.println(result);
	}

	public static int valueof(LetLangExp LLE, Environment env) {
		// Variabletype a1
		System.out.println(LLE.Vari);
		switch(LLE.Vari) {
			case let: {
				SampleEnv eu = new SampleEnv();
				LetLangExp id = LLE.getindetifier();
				String e12 = id.getVar();
				LetLangExp LE = LLE.getletexp1();
				// env.Extendenv(eu, LE.e.getVar(), M);
				int M = valueof(LE, env);
				LetLangExp id2 = LLE.getindetifier();
				env.Extendenv(eu, id2.getVar(), M);
				int o = valueof(LLE.getletexp2(), env);
				System.out.println(o);
				return o;
			}
			case minus: {
				SampleEnv eu = new SampleEnv();
				int a1 = valueof(LLE.getdiffexp1(), env);
				int a2 = valueof(LLE.getdiffexp2(), env);
				return a2 - a1;
			}
			case number: {
				SampleEnv eu = new SampleEnv();
				return LLE.getnum();
			}
			case Var: {
				SampleEnv eu = new SampleEnv();
				//			LetLangExp id = LLE.getindetifier();
				//			String e12 = id.getVar();
				return env.applyenv(eu, LLE.getVar());
			}
		}
		return 0;
	}

	public static int valueOf(LetLangExp exp, Env env) {
		switch(exp.Vari) {
			case let: {
				int e1Value = valueOf(exp.getletexp1(), env);
				Env subEnv = new Env(exp.getindetifier().getVar(), e1Value, env);
				return valueOf(exp.getletexp2(), subEnv);
			}
			case minus: {
				int a1 = valueOf(exp.getdiffexp1(), env);
				int a2 = valueOf(exp.getdiffexp2(), env);
				return a1 - a2;
			}
			case number: {
				return exp.getnum();
			}
			case Var: {
				return env.getValue(exp.getVar());
			}
			default: {
				throw new RuntimeException("not implemented");
			}
		}
	}
}
