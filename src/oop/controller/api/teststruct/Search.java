package oop.controller.api.teststruct;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<TestStructure> testStructures = TestStructureDAO.fetchByName(
				"%" + query + "%", 20);
		
		JSONArray suggestions = new JSONArray();
		JSONArray data = new JSONArray();
		for (TestStructure struct : testStructures) {
			suggestions.put(struct.getName());
			data.put(struct.getId());
		}
		
		JSONObject result = new JSONObject();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
