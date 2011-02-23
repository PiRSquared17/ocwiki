package org.ocwiki.controller.action.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Test;
import org.ocwiki.db.dao.TestDAO;

public class ListAction extends AbstractAction {

	public static final int PAGE_LENGTH = 30;
	private long count;
	private int start;
	private int size;
	private List<Resource<Test>> tests;

	@Override
	public void performImpl() throws IOException, ServletException {
		title("Danh sách đề thi");
		start = getParams().getInt("start", 0);
		size = getParams().getInt("size", PAGE_LENGTH);
		tests = TestDAO.fetchOrderByName(start, size);
		count = TestDAO.count();
		request.setAttribute("tests", tests);
	}

	public long getCount() {
		return count;
	}

	public int getStart() {
		return start;
	}

	public int getSize() {
		return size;
	}

	public List<Resource<Test>> getTests() {
		return tests;
	}

}
