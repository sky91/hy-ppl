package yi.letlangproj;

import java.util.Objects;

public class Env {
	private final String k;
	private final int v;
	private final Env parentEnv;

	public Env(String k, int v, Env parentEnv) {
		this.k = k;
		this.v = v;
		this.parentEnv = parentEnv;
	}

	public int getValue(String name) {
		Integer value = null;
		if(Objects.equals(name, k)) {
			return v;
		}
		if(parentEnv != null) {
			value = parentEnv.getValue(name);
		}
		if(value == null) {
			throw new RuntimeException("Cannot find value for [" + name + "]");
		}
		return value;
	}
}
