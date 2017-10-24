package yi.letlangproj;

import yi.letlangproj.LetScanner.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String... args) {
		String filePathString = args.length > 0 ? args[0] : new Scanner(System.in).nextLine();
		String fileContent;
		try {
			byte[] fileBytes = Files.readAllBytes(Paths.get(filePathString));
			fileContent = new String(fileBytes);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		ArrayList<Token> sclist = LetScanner.lex(fileContent);
		for(Token i : sclist) {
			System.out.println(i);
		}
		LetLangExp Lc = Parser.returnParser(fileContent);
		int result = valueOf(Lc, null);
		System.out.println(result);
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
