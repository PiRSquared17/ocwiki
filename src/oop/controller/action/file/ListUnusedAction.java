package oop.controller.action.file;

import java.util.List;

import oop.controller.action.AbstractAction;
import oop.controller.action.AbstractResourceAction;
import oop.controller.action.ActionException;
import oop.data.File;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.FileDAO;
import oop.db.dao.TopicDAO;

public class ListUnusedAction extends AbstractAction {

		private List<Resource<File>> unusedFiles;

		@Override
		public void performImpl() throws Exception {
			int start = getParams().getInt("start", 0);
			int size = 20;
			unusedFiles = FileDAO.fetchUnused(start,size);
		}

		public List<Resource<File>> getUnusedFiles() {
			return unusedFiles;
		}
		
		

}
