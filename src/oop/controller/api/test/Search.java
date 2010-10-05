package oop.controller.api.test;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Resource;
import oop.data.Test;
import oop.db.dao.TestDAO;
import oop.util.JsonUtils;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<Test>> tests = TestDAO.fetchByNameLike("%" + query + "%", 20);
		
		ArrayNode suggestions = JsonUtils.getFactory().arrayNode();
		ArrayNode data = JsonUtils.getFactory().arrayNode();
		for (Resource<Test> test : tests) {
			suggestions.add(test.getName());
			data.add(test.getId());
		}
		
		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
