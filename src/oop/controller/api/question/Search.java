package oop.controller.api.question;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;
import oop.util.JsonUtils;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<BaseQuestion>> questions = BaseQuestionDAO
				.fetchByContent("%" + query + "%", 20);

		ArrayNode suggestions = JsonUtils.getFactory().arrayNode();
		ArrayNode data = JsonUtils.getFactory().arrayNode();
		for (Resource<BaseQuestion> question : questions) {
			suggestions.add(JsonUtils.getFactory().textNode(question.getName()));
			data.add(JsonUtils.getFactory().numberNode(question.getId()));
		}

		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
