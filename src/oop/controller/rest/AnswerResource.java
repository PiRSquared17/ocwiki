package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.controller.rest.util.ResourceUtil;
import oop.data.Answer;
import oop.data.Text;
import oop.db.dao.AnswerDAO;

@Path("/answers")
public class AnswerResource extends AbstractResource {

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Answer> get(@PathParam("id") long id) {
		Answer answer = AnswerDAO.fetch(id);
		ResourceUtil.assertFound(answer);
		return new ObjectResult<Answer>(answer);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Answer> create(
			@FormParam("question") long questionId,
			@FormParam("content") String contentStr,
			@DefaultValue("false") @FormParam("correct") boolean correct) {
		ResourceUtil.assertParamNotEmpty("content", contentStr);
		Answer answer = AnswerDAO.create(questionId, contentStr, correct);
		return new ObjectResult<Answer>(answer);
	}
	
	@PUT
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Answer> update(
			@PathParam("id") long id, 
			@FormParam("content") String contentStr,
			@DefaultValue("false") @FormParam("correct") boolean correct) {
		Answer answer = AnswerDAO.fetch(id);
		ResourceUtil.assertFound(answer);
		ResourceUtil.assertParamNotEmpty("content", contentStr);
		answer.setContent(new Text(contentStr));
		answer.setCorrect(correct);
		return new ObjectResult<Answer>(answer);
	}
	
	@DELETE
	@Path("/{id: \\d+}")
	public void delete(@PathParam("id") long id) {
		AnswerDAO.delete(id);
	}
	
}
