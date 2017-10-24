package ppl;

public class Environment {
	public static SampleEnv env;

	public static void main(String arg[]) {
		Environment E = new Environment();
		String s = "turn";
		int y = 5;
		// public static SampleEnv env;
		env = new SampleEnv();
		E.Extendenv(env, s, y);
		System.out.println(E.applyenv(env, s));
	}
	// private static Environment EMPTY_ENVIRONMENT = new Environment("StubVar",
	// -9);
	// public HashMap<String, Integer> storevalues = new HashMap<String, Integer>();

	/*
	 * public Environment() { m_Variable = p_Variable; m_Value = p_Value; }
	 */

	public SampleEnv empty_env() {
		env = new SampleEnv();
		// ArrayList<String> mylist = new ArrayList<String>();
		return env;
	}

	public SampleEnv Extendenv(SampleEnv env, String Variable, int value) {
		// storevalues.put(Variable, value);
		env.vvalue = value;
		env.variabel = Variable;
		env.map.put(env.variabel, env.vvalue);
		return env;
	}

	public int applyenv(SampleEnv env, String variable) {
		if(env.map.get(variable) != null) {
			return env.map.get(variable);
		} else {
			return 0;
		}
	}
}
