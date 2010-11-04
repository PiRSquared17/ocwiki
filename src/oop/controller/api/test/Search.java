package oop.controller.api.test;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.TestDAO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<Test>> tests = TestDAO.fetchByNameLike("%" + query + "%", 20);
		
		JSONArray suggestions = new JSONArray();
		JSONArray data = new JSONArray();
		for (Resource<Test> test : tests) {
			suggestions.put(test.getName());
			data.put(test.getId());
		}
		
		JSONObject result = new JSONObject();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
