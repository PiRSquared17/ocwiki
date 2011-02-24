package org.ocwiki.controller.api.question;

import java.util.List;

import org.ocwiki.controller.api.AbstractAPI;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().get("query");
		List<Resource<MultichoiceQuestion>> questions = MultichoiceQuestionDAO
				.fetchByContent("%" + query + "%", 20);

		JSONArray suggestions = new JSONArray();
		JSONArray data = new JSONArray();
		for (Resource<MultichoiceQuestion> question : questions) {
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
