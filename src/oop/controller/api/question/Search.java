package oop.controller.api.question;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Article;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<BaseQuestion> questions = BaseQuestionDAO.fetchByContent("%"
				+ query + "%", 20);
		
		JsonObject result = new JsonObject();
		result.addProperty("query", query);
		JsonArray suggestions = new JsonArray();
		result.add("suggestions", suggestions);
		JsonArray data = new JsonArray();
		result.add("data", data);
		for (Article question : questions) {
			suggestions.add(new JsonPrimitive(question.getName()));
			data.add(new JsonPrimitive(question.getId()));
		}
		return result;
	}

}
