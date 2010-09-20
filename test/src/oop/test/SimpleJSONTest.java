package oop.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class SimpleJSONTest {

	void fromJSON() {
		System.out.println(new Gson().fromJson("{\"i\":3,\"j\":5}", Data.class));
	}

	void toJSON() {
		System.out.println(new Gson().toJson(new Data(3, 5)));
	}
	
	void mapFromJSON() {
		System.out.println(new Gson().fromJson("{\"b\":\"abcdef\",\"a\":12}", HashMap.class));
//		Map<String,Object> map = new Gson().fromJson("{\"b\":\"abcdef\",\"a\":12}", 
//				new TypeToken<Map<String,Object>>() {}.getType());
//		System.out.println(map.get("a"));
//		System.out.println(map.get("b"));
	}
	
	void mapToJSON() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 12);
		map.put("b", "abcdef");
		System.out.println(new Gson().toJson(map));
	}
	
	void notReallyToJSON() {
		Data data = new Data(3, 5);
		System.out.println(data.getClass().getName());
		System.out.println(new Gson().toJson(data));
	}
	
	@SuppressWarnings("unchecked")
	void notReallyFromJSON() {
		try {
			Class clazz = Class.forName("oop.test.SimpleJSONTest$Data");
			Data data = new Gson().fromJson("{\"i\":3,\"j\":5}", clazz);
			System.out.println(data);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SimpleJSONTest().notReallyFromJSON();
	}

	static class Data {
		int i;
		int j;
		int k;

		public Data() {
		}

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "i:" + i + ",j:" + j + ",k:" + k;
		}
	}

}
