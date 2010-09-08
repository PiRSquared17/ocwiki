package oop.controller.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Namespace;
import oop.data.Resource;
import oop.db.dao.ResourceDAO;

@Path("/bquestions")
public class BaseQuestionResource extends AbstractResource {

	@GET
	public ListResult<Resource<BaseQuestion>> list(
			@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size) {
		assertParamValid(size <= MAX_PAGE_SIZE, "size", "too large");
		List<Resource<BaseQuestion>> list = ResourceDAO.fetchByNamespace(
				Namespace.QUESTION, start, size);
		String nextUrl = null;
		if (list.size() >= size) {
			nextUrl = "/questions?start=" + (start + size) + "&size=" + size;
		}
		return new ListResult<Resource<BaseQuestion>>(list, nextUrl);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Resource<BaseQuestion>> get(@PathParam("id") long id) {
		Resource<BaseQuestion> resource = getResourceSafe(id,
				BaseQuestion.class);
		return new ObjectResult<Resource<BaseQuestion>>(resource);
	}

}
