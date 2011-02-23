package org.ocwiki.controller.action.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.File;
import org.ocwiki.data.Resource;
import org.ocwiki.db.dao.FileDAO;

public class ListUnusedAction extends AbstractAction {

		private List<Resource<File>> unusedFiles;
		private long count;
		private long curStart;
		
		@Override
		public void performImpl() throws IOException, ServletException {
			int start = getParams().getInt("start", 0);
			int size = 20;
			title("Dang sách các tệp tin chưa được sử dụng, trang " + ((start/size)+1));
			unusedFiles = FileDAO.fetchUnused(start,size);
			count = FileDAO.countUnused();
		}

		public List<Resource<File>> getUnusedFiles() {
			return unusedFiles;
		}

		public long getCount() {
			return count;
		}

		public long getCurStart() {
			return curStart;
		}
}
