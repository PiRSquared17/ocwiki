package oop.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.bean.MapperUtils;
import oop.controller.rest.bean.ResourceSearchReportBean;
import oop.controller.rest.bean.ResourceSearchReportMapper;
import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Question;
import oop.data.Resource;
import oop.data.ResourceSearchReport;
import oop.data.Revision;
import oop.data.Section;
import oop.data.Test;
import oop.data.Text;
import oop.db.dao.ArticleDAO;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;

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
	public ObjectResult<Test> add(Test test) {
		validate(test);
		ResourceDAO.create(getUserNullSafe(), Test.class, test);
		return new ObjectResult<Test>(test);
	}

	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Test> get(@PathParam("id") long resourceId) {
		Resource<Test> resource = getResourceSafe(resourceId, Test.class);
		Test test = resource.getArticle();
		return new ObjectResult<Test>(test);
	}

	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<Test> update(@PathParam("id") long resourceId, Revision<Test> data)
			throws Exception {
		Resource<Test> resource = getResourceSafe(resourceId, Test.class);
		validate(data.getArticle());
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");

		Test test = data.getArticle().copy();
		copySections(data.getArticle(), test);
		WebServiceUtils.copyTopics(test, data.getArticle());
		ArticleDAO.persist(test);

		saveNewRevision(resource, data.getArticle(), data.getSummary(), data
				.isMinor());
		return new ObjectResult<Test>(resource.getArticle());
	}

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

	private void validate(Test test) {
		WebServiceUtils.assertValid(test != null, "test is empty");
		WebServiceUtils.assertValid(Text.isNotBlank(test.getContent()),
				"test content is blank");
		WebServiceUtils.assertValid(
				CollectionUtils.size(test.getSections()) > 0,
				"too little sections");
		for (Section section : test.getSections()) {
			WebServiceUtils.assertValid(CollectionUtils.size(section
					.getQuestions()) > 0, "too little questions");
			WebServiceUtils.assertValid(Text.isNotBlank(section.getContent()),
					"section content is blank");
			for (Question question : section.getQuestions()) {
				WebServiceUtils.assertValid(question != null
						&& question.getBase() != null, "question is empty");
				WebServiceUtils.assertValid(question.getLevel() >= 1
						&& question.getLevel() <= 5, "level out of range");
				WebServiceUtils.assertValid(question.getMark() <= 0,
						"mark out of range");
			}
		}
	}

}
