package oop.controller.rest.resources.basequestion;

import java.util.List;

import javax.ws.rs.Path;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.WebServiceUtils;
import oop.controller.rest.bean.AnswerBean;
import oop.controller.rest.bean.BaseQuestionBean;
import oop.controller.rest.bean.BaseQuestionMapper;
import oop.controller.rest.bean.MapperUtils;
import oop.controller.rest.bean.ResourceSearchReportBean;
import oop.controller.rest.bean.ResourceSearchReportMapper;
import oop.controller.rest.bean.RevisionBean;
import oop.controller.rest.bean.TextBean;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.ResourceSearchReport;
import oop.data.Status;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;

import org.apache.commons.collections.CollectionUtils;

@Path(BaseQuestionServiceImpl.PATH)
public class BaseQuestionServiceImpl extends AbstractResource implements
		BaseQuestionService {

	public static final String PATH = "/questions";

	@Override
	public ObjectResult<BaseQuestionBean> add(BaseQuestionBean bean)
			throws Exception {
		validate(bean); 
		BaseQuestion question = BaseQuestionMapper.get().toEntity(bean);
		ResourceDAO.create(getUserNullSafe(), BaseQuestion.class, question);
		bean = BaseQuestionMapper.get().toBean(question);
		return new ObjectResult<BaseQuestionBean>(bean);
	}

	@Override
	public ObjectResult<BaseQuestionBean> get(long resourceId) throws Exception {
		Resource<BaseQuestion> resource = getResourceSafe(resourceId,
				BaseQuestion.class);
		BaseQuestion question = resource.getArticle();
		BaseQuestionBean bean = BaseQuestionMapper.get().toBean(question);
		return new ObjectResult<BaseQuestionBean>(bean);
	}

	@Override
	public ObjectResult<BaseQuestionBean> update(long resourceId,
			RevisionBean<BaseQuestionBean> data) throws Exception {
		Resource<BaseQuestion> resource = getResourceSafe(resourceId,
				BaseQuestion.class);
		if (resource.getStatus() != Status.NEW) {
			validate(data.getArticle());
		}
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		BaseQuestion question = BaseQuestionMapper.get().toEntity(data.getArticle());
		question.setId(0); // coi nó như đối tượng mới
		ArticleDAO.persist(question);

		saveNewRevision(resource, question, data.getSummary(), data.isMinor());
		BaseQuestionBean bean = BaseQuestionMapper.get().toBean(resource.getArticle());
		return new ObjectResult<BaseQuestionBean>(bean);
	}

	private void validate(BaseQuestionBean question) {
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

	@Override
	public ListResult<ResourceSearchReportBean> listByRelatedResource(
			long resourceID) {
		List<ResourceSearchReport<BaseQuestion>> questions = ArticleDAO
				.fetchRelated(BaseQuestion.class, resourceID, 0, 5);
		List<ResourceSearchReportBean> beans = MapperUtils.toBeans(questions,
				ResourceSearchReportMapper.get());
		return new ListResult<ResourceSearchReportBean>(beans);
	}

}
