package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Text;
import oop.db.dao.ResourceDAO;

@Path("/answers")
public class AnswerResource extends AbstractResource {

	@GET
	@Path("/question/{questionId: \\d+}/{index: \\d+}")
	public ObjectResult<Answer> get(@PathParam("questionId") long questionId,
			@PathParam("index") int index) {
		BaseQuestion question = (BaseQuestion) ResourceDAO
				.fetchById(questionId).getArticle();
		Answer answer = question.getAnswers().get(index);
		return new ObjectResult<Answer>(answer);
	}

	@GET
	@Path("/question/{questionId: \\d+}")
	public ListResult<Answer> listByQuestion(@PathParam("questionId") long questionId) {
		BaseQuestion question = (BaseQuestion) ResourceDAO
				.fetchById(questionId).getArticle();
		assertResourceFound(question);
		return new ListResult<Answer>(question.getAnswers());
	}

	@POST
	@Path("/question/{questionId: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Answer> create(
			@PathParam("questionId") long questionId,
			@FormParam("content") String contentStr,
			@DefaultValue("false") @FormParam("correct") boolean correct) {
		assertParamFound("content");
		Resource<BaseQuestion> resource = ResourceDAO.fetchById(questionId,
				BaseQuestion.class);
		BaseQuestion question = resource.getArticle().copy();
		Answer answer = new Answer(new Text(contentStr), correct);
		question.getAnswers().add(answer);
		saveNewRevision(resource, question);
		return new ObjectResult<Answer>(answer);
	}

	@POST
	@Path("/question/{questionId: \\d+}/{index: \\d+}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<Answer> update(
			@PathParam("questionId") long questionId,
			@PathParam("index") int index,
			@FormParam("content") String contentStr,
			@DefaultValue("false") @FormParam("correct") boolean correct) {
		assertParamFound("content");
		Resource<BaseQuestion> resource = ResourceDAO.fetchById(questionId,
				BaseQuestion.class);
		BaseQuestion question = resource.getArticle().copy();
		Answer answer = question.getAnswers().get(index).copy();
		answer.setContent(new Text(contentStr));
		answer.setCorrect(correct);
		question.getAnswers().set(index, answer);
		saveNewRevision(resource, question);
		return new ObjectResult<Answer>(answer);
	}

	@DELETE
	@Path("/question/{questionId: \\d+}/{index: \\d+}")
	public void delete(@PathParam("questionId") long questionId,
			@PathParam("index") int index) {
		Resource<BaseQuestion> resource = ResourceDAO.fetchById(questionId,
				BaseQuestion.class);
		BaseQuestion question = resource.getArticle().copy();
		question.getAnswers().remove(index);
		saveNewRevision(resource, question);
	}

}
