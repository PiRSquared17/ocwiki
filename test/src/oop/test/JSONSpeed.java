package oop.test;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import oop.controller.api.APIResult;

public class JSONSpeed {

	static final int TIMES = 20000;
	
	int id = 1000;
	String text = "Vietnam Vietnam Vietnam Vietnam ";
	
	@Test
	public void convert() {
		String json = "";
		for (int i = 0; i < TIMES; i++) {
			Result result = new Result();
			result.id = id;
			result.text = text;
			json = new Gson().toJson(result);
		}
		System.out.println(json);
	}
	
	@Test
	public void build() {
		String json = "";
		for (int i = 0; i < TIMES; i++) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "successful");
			result.addProperty("id", id);
			result.addProperty("text", text);
			json = result.toString();
		}
		System.out.println(json);
	}

	@Test
	public void string() {
		String json = "";
		for (int i = 0; i < TIMES; i++) {
			StringBuilder sb = new StringBuilder("{");
			sb.append("status:\"successful\",\"id\":").append(id).append(
					",\"text\":\"").append(text);
	}
		System.out.println(json);
	}

	static class Result extends APIResult {
		long id;
		String text;
	}
	
}
