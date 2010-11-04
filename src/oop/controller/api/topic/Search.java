package oop.controller.api.topic;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().getString("query").replaceAll("%", "%%");
		List<Resource<Topic>> topics = TopicDAO.fetchByNameLike("%" + query + "%");
		
		JSONArray suggestions = new JSONArray();
		JSONArray data = new JSONArray();
		for (Resource<Topic> topic : topics) {
			suggestions.put(topic.getName());
			data.put(topic.getId());
		}
		JSONObject result = new JSONObject();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
