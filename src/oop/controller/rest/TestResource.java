package oop.controller.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.bean.MapperUtils;
import oop.controller.rest.bean.QuestionBean;
import oop.controller.rest.bean.ResourceSearchReportBean;
import oop.controller.rest.bean.ResourceSearchReportMapper;
import oop.controller.rest.bean.RevisionBean;
import oop.controller.rest.bean.SectionBean;
import oop.controller.rest.bean.TestBean;
import oop.controller.rest.bean.TestMapper;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Resource;
import oop.data.ResourceSearchReport;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;

import org.apache.commons.collections.CollectionUtils;

@Path(TestResource.PATH)
public class TestResource extends AbstractResource {

	public static final String PATH = "/tests";

	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReportBean> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<Test>> listRelatedTest = ArticleDAO
				.fetchRelated(Test.class, resourceID, 0, 5);
		List<ResourceSearchReportBean> beans = MapperUtils.toBeans(
				listRelatedTest, ResourceSearchReportMapper.get());
		return new ListResult<ResourceSearchReportBean>(beans);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<TestBean> add(TestBean bean) {
		validate(bean);
		Test test = TestMapper.get().toEntity(bean);
		ResourceDAO.create(getUserNullSafe(), Test.class, test);
		bean = TestMapper.get().toBean(test);
		return new ObjectResult<TestBean>(bean);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TestBean> get(@PathParam("id") long resourceId) {
		Resource<Test> resource = getResourceSafe(resourceId, Test.class);
		Test test = resource.getArticle();
		TestBean bean = TestMapper.get().toBean(test);
		return new ObjectResult<TestBean>(bean);
	}

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<TestBean> update(@PathParam("id") long resourceId,
			RevisionBean<TestBean> data) throws Exception {
		validate(data.getArticle());
		Resource<Test> resource = getResourceSafe(resourceId, Test.class);
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		Test test = TestMapper.get().toEntity(data.getArticle());
		test.setId(0); // mark as new
//		copySections(data.getArticle(), test);
//		WebServiceUtils.copyTopics(test, data.getArticle());
		ArticleDAO.persist(test);

		saveNewRevision(resource, test, data.getSummary(), data.isMinor());
		TestBean bean = TestMapper.get().toBean(test);
		return new ObjectResult<TestBean>(bean);
	}

	/*
	private void copySections(Test src, Test dest) {
		List<Section> newSections = new ArrayList<Section>();
		for (Section section : src.getSections()) {
			if (section.getId() <= 0) {
				for (Question question : section.getQuestions()) {
					if (question.getId() <= 0) {
						if (question.getBaseRevision() != null) {
							Revision<BaseQuestion> revision = RevisionDAO
									.fetch(question.getBaseRevision().getId(),
											BaseQuestion.class);
							question.setBaseContainer(revision);
						} else if (question.getBaseResource() != null) {
							Resource<BaseQuestion> resource = ResourceDAO
									.fetchById(question.getBaseResource()
											.getId(), BaseQuestion.class);
							question.setBaseContainer(resource);
						}
					}
				}
			}
			newSections.add(section);
		}
		dest.setSections(newSections);
	}
	*/

	private void validate(TestBean bean) {
		WebServiceUtils.assertValid(bean != null, "test is empty");
		WebServiceUtils.assertValid(Text.isNotBlank(bean.getContent()),
				"test content is blank");
		WebServiceUtils.assertValid(
				CollectionUtils.size(bean.getSections()) > 0,
				"too little sections");
		for (SectionBean section : bean.getSections()) {
			WebServiceUtils.assertValid(CollectionUtils.size(section
					.getQuestions()) > 0, "too little questions");
			WebServiceUtils.assertValid(Text.isNotBlank(section.getContent()),
					"section content is blank");
			for (QuestionBean question : section.getQuestions()) {
				WebServiceUtils.assertValid(question != null
						&& question.getBase() != null, "question is empty");
				WebServiceUtils.assertValid(question.getMark() > 0,
						"mark must be positive");
			}
		}
	}

}
