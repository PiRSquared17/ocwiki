package oop.controller.api.topic;

import java.util.List;

import oop.controller.api.AbstractAPI;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;
import oop.util.JsonUtils;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public class Search extends AbstractAPI {

	@Override
	public Object performImpl() throws Exception {
		String query = getParams().getString("query").replaceAll("%", "%%");
		List<Resource<Topic>> topics = TopicDAO.fetchByNameLike("%" + query + "%");
		
		ArrayNode suggestions = JsonUtils.getFactory().arrayNode();
		ArrayNode data = JsonUtils.getFactory().arrayNode();
		for (Resource<Topic> topic : topics) {
			suggestions.add(topic.getName());
			data.add(topic.getId());
		}
		ObjectNode result = JsonUtils.getFactory().objectNode();
		result.put("query", query);
		result.put("suggestions", suggestions);
		result.put("data", data);
		return result;
	}

}
