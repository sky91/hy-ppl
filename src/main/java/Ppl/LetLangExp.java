package Ppl;

import java.util.ArrayList;

public class LetLangExp {
	public static ArrayList<String> tokens = new ArrayList<String>();
	ArrayList<String> tokens1 = new ArrayList<String>();
	protected Variabletype Vari;

	public static void main(String args[]) {
		parser ps = new parser();
		tokens = ps.returnParser();
	}

	public String getVar() {
		return null;
	}

	public LetLangExp getletexp1() {
		return null;
	}

	public LetLangExp getletexp2() {
		return null;
	}

	public LetLangExp getindetifier() {
		return null;
	}

	public int getnum() {
		return 0;
	}

	public LetLangExp getdiffexp1() {
		return null;
	}

	public LetLangExp getdiffexp2() {
		return null;
	}

	/*
	 * public LetLangExp() { String
	 * 
	 * }
	 */
	public class ConstExp extends LetLangExp {
		int value;

		public ConstExp(int value1) {
			this.value = value1;
			super.Vari = Variabletype.number;
		}

		public ConstExp() {
			super.Vari = Variabletype.number;
		}

		public int getnum() {
			return value;
		}
	}

	public class BoolExp extends LetLangExp {
		Integer value;

		public BoolExp(Integer value) {
			this.value = value;
		}
	}

	public class DiffExp extends LetLangExp {
		private LetLangExp e1;
		private LetLangExp e2;

		public DiffExp() {
			super.Vari = Variabletype.minus;
		}

		public DiffExp(LetLangExp e1, LetLangExp e2) {
			this.e1 = e1;
			this.e2 = e2;
			super.Vari = Variabletype.minus;
		}

		public LetLangExp getdiffexp1() {
			return e1;
		}

		public LetLangExp getdiffexp2() {
			return e2;
		}
		// public Expression
	}

	public class IsZeroExp extends LetLangExp {}

	public class IfExp extends LetLangExp {}

	public class VarExp extends LetLangExp {
		private String e;

		public VarExp() {
			super.Vari = Variabletype.Var;
		}

		public VarExp(String e) {
			this.e = e;
			super.Vari = Variabletype.Var;
		}

		public String getVar() {
			return e;
		}
	}

	public class LetExp extends LetLangExp {
		public LetLangExp e;
		public LetLangExp e1;
		public LetLangExp e2;

		public LetExp() {
			super.Vari = Variabletype.let;
		}

		public LetExp(LetLangExp e, LetLangExp e1, LetLangExp e2) {
			this.e = e;
			this.e1 = e1;
			this.e2 = e2;
			super.Vari = Variabletype.let;
		}

		public LetLangExp getindetifier() {
			return e;
		}

		public LetLangExp getletexp1() {
			return e1;
		}

		public LetLangExp getletexp2() {
			return e2;
		}
	}
}