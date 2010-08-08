package oop.controller.api.answer;

import com.google.gson.JsonObject;

import oop.controller.api.AbstractAPI;
import oop.data.Answer;
import oop.db.dao.AnswerDAO;

public class Edit extends AbstractAPI {

	@Override
	protected Object performImpl() throws Exception {
		Answer answer = AnswerDAO.fetch(getParams().getLong("id"));
		if (answer == null) {
			return fail().addProperty("errorCode", "not found");
		}
		if ("save".equals(getParams().get("op"))) {
			 
		} else {
			return success().add(
					"answer", new JsonObject()
							.addProperty("correct", answer.isCorrect())
							.addProperty("content", answer.getContent().getText()));
		}
		return fail().addProperty("errorCode", "unknown operation");
	}

}
