package org.ocwiki.controller.api.test;

import java.util.List;

import org.ocwiki.controller.api.AbstractAPI;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.TestDAO;

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
