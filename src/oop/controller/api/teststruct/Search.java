package oop.controller.api.teststruct;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.TestStructure;
import oop.db.dao.TestStructureDAO;
import oop.util.JsonUtils;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<TestStructure> testStructures = TestStructureDAO.fetchByName(
				"%" + query + "%", 20);
		
		ArrayNode suggestions = JsonUtils.getFactory().arrayNode();
		ArrayNode data = JsonUtils.getFactory().arrayNode();
		for (TestStructure struct : testStructures) {
			suggestions.add(struct.getName());
			data.add(struct.getId());
		}
		
		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
