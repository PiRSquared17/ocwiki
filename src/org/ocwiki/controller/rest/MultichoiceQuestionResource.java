package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.ocwiki.controller.rest.bean.AnswerBean;
import org.ocwiki.controller.rest.bean.MapperUtils;
import org.ocwiki.controller.rest.bean.MultichoiceQuestionBean;
import org.ocwiki.controller.rest.bean.MultichoiceQuestionMapper;
import org.ocwiki.controller.rest.bean.ResourceSearchReportBean;
import org.ocwiki.controller.rest.bean.ResourceSearchReportMapper;
import org.ocwiki.controller.rest.bean.RevisionBean;
import org.ocwiki.controller.rest.bean.TextBean;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.ResourceSearchReport;
import org.ocwiki.db.dao.ArticleDAO;
import org.ocwiki.db.dao.ResourceDAO;

@Path(MultichoiceQuestionResource.PATH)
public class MultichoiceQuestionResource extends AbstractResource  {

	public static final String PATH = "/questions";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<MultichoiceQuestionBean> add(MultichoiceQuestionBean bean)
			throws Exception {
		validate(bean); 
		MultichoiceQuestion question = MultichoiceQuestionMapper.get().toEntity(bean);
		ResourceDAO.create(getUserNullSafe(), MultichoiceQuestion.class, question);
		bean = MultichoiceQuestionMapper.get().toBean(question);
		return new ObjectResult<MultichoiceQuestionBean>(bean);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<MultichoiceQuestionBean> get(long resourceId) throws Exception {
		Resource<MultichoiceQuestion> resource = getResourceSafe(resourceId,
				MultichoiceQuestion.class);
		MultichoiceQuestion question = resource.getArticle();
		MultichoiceQuestionBean bean = MultichoiceQuestionMapper.get().toBean(question);
		return new ObjectResult<MultichoiceQuestionBean>(bean);
	}

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<MultichoiceQuestionBean> update(long resourceId,
			RevisionBean<MultichoiceQuestionBean> data) throws Exception {
		Resource<MultichoiceQuestion> resource = getResourceSafe(resourceId,
				MultichoiceQuestion.class);
		validate(data.getArticle());
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		MultichoiceQuestion question = MultichoiceQuestionMapper.get().toEntity(data.getArticle());
		question.setId(0); // coi nó như đối tượng mới
		ArticleDAO.persist(question);
		saveNewRevision(resource, question, data.getSummary(), data.isMinor());

		MultichoiceQuestionBean bean = MultichoiceQuestionMapper.get().toBean(resource.getArticle());
		return new ObjectResult<MultichoiceQuestionBean>(bean);
	}

	private void validate(MultichoiceQuestionBean question) {
		WebServiceUtils.assertValid(question != null, "question is empty");
		WebServiceUtils.assertValid(TextBean.isNotBlank(question.getContent()),
				"question content is blank");
		WebServiceUtils.assertValid(
				CollectionUtils.size(question.getAnswers()) >= 2,
				"too little answers");
		WebServiceUtils.assertValid(
				CollectionUtils.size(question.getAnswers()) < 10,
				"too many answers");
		boolean hasCorrect = false;
		for (AnswerBean answer : question.getAnswers()) {
			WebServiceUtils.assertValid(answer != null, "answer is empty");
			WebServiceUtils.assertValid(TextBean.isNotBlank(answer.getContent()),
					"answer content is blank");
			if (answer.isCorrect()) {
				hasCorrect = true;
			}
		}
		WebServiceUtils.assertValid(hasCorrect, "no correct answer");
	}

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReportBean> listByRelatedResource(
			long resourceID) {
		List<ResourceSearchReport<MultichoiceQuestion>> questions = ArticleDAO
				.fetchRelated(MultichoiceQuestion.class, resourceID, 0, 5);
		List<ResourceSearchReportBean> beans = MapperUtils.toBeans(questions,
				ResourceSearchReportMapper.get());
		return new ListResult<ResourceSearchReportBean>(beans);
	}

}
