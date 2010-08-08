package oop.controller.api.teststruct;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<TestStructure> testStructures = TestStructureDAO.fetchByName(
				"%" + query + "%", 20);
		
		JsonObject result = new JsonObject();
		result.addProperty("query", query);
		JsonArray suggestions = new JsonArray();
		result.add("suggestions", suggestions);
		JsonArray data = new JsonArray();
		result.add("data", data);
		for (TestStructure struct : testStructures) {
			suggestions.add(new JsonPrimitive(struct.getName()));
			data.add(new JsonPrimitive(struct.getId()));
		}
		return result;
	}

}
