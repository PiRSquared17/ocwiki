package oop.controller.rest.resources.basequestion;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Revision;

public interface BaseQuestionService {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<BaseQuestion> add(BaseQuestion question)
			throws Exception;

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<BaseQuestion> get(@PathParam("id") long resourceId)
			throws Exception;

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<BaseQuestion> update(@PathParam("id") long resourceId,
			Revision<BaseQuestion> data) throws Exception;

}
