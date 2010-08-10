package oop.controller.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.controller.rest.util.ResourceUtil;
import oop.data.BaseQuestion;
import oop.db.dao.BaseQuestionDAO;

@Path("/questions")
public class QuestionResource extends AbstractResource {

	public static final int MAX_PAGE_SIZE = 50;
	
	@GET
	public ListResult<BaseQuestion> list(
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		ResourceUtil.assertParamValid(size <= MAX_PAGE_SIZE, "size", size,
				"too large");
		List<BaseQuestion> list = BaseQuestionDAO.fetch(start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/questions?start=" + (start + size) + "&size=" + size;
		}
		return new ListResult<BaseQuestion>(list, nextUrl);
	}
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<BaseQuestion> get(@PathParam("id") long id) {
		BaseQuestion question = BaseQuestionDAO.fetchById(id);
		ResourceUtil.assertFound(question);
		return new ObjectResult<BaseQuestion>(question);
	}
	
}
