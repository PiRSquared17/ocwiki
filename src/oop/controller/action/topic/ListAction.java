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

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Danh sách chủ đề");
		topLevels = TopicDAO.fetchTopLevels();
	}
	
	public List<Resource<Topic>> getTopLevels() {
		return topLevels;
	}

}
