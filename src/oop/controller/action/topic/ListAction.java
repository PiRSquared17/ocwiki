package oop.controller.action.topic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import oop.controller.action.AbstractAction;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

public class ListAction extends AbstractAction {

	private List<Resource<Topic>> topLevels;
	private long count;
	private int start;

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Danh sách chủ đề");
		start = getParams().getInt("start", 0);
		int size = getParams().getInt("size", 20);
		topLevels = TopicDAO.listOrderByName(start, size);
		count = TopicDAO.count();
	}
	
	public List<Resource<Topic>> getTopics() {
		return topLevels;
	}
	
	public int getStart() {
		return start;
	}
	
	public long getCount() {
		return count;
	}

}
