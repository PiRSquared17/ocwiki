package oop.controller.api.question;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.db.dao.BaseQuestionDAO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<BaseQuestion>> questions = BaseQuestionDAO
				.fetchByContent("%" + query + "%", 20);

		JSONArray suggestions = new JSONArray();
		JSONArray data = new JSONArray();
		for (Resource<BaseQuestion> question : questions) {
			suggestions.put(question.getName());
			data.put(question.getId());
		}

		JSONObject result = new JSONObject();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
