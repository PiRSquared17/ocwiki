package oop.util;

import com.google.gson.Gson;

public final class GsonFactory {

	private GsonFactory() {
	}

	private static ThreadLocal<Gson> gsonLocal = new ThreadLocal<Gson>();
	
	public static Gson get() {
		if (gsonLocal.get() == null) {
			gsonLocal.set(createGson());
		}
		return gsonLocal.get();
	}

	private static Gson createGson() {
		return new Gson();
	}
	
}
