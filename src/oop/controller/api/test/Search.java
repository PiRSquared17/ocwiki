package oop.controller.api.test;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.TestDAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<Test>> tests = TestDAO.fetchByNameLike("%" + query + "%", 20);
		
		JsonObject result = new JsonObject();
		result.addProperty("query", query);
		JsonArray suggestions = new JsonArray();
		result.add("suggestions", suggestions);
		JsonArray data = new JsonArray();
		result.add("data", data);
		for (Resource<Test> test : tests) {
			suggestions.add(new JsonPrimitive(test.getName()));
			data.add(new JsonPrimitive(test.getId()));
		}
		return result;
	}

}
