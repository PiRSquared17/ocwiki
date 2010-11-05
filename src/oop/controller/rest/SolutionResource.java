package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.bean.RevisionBean;
import oop.controller.rest.bean.SolutionBean;
import oop.controller.rest.bean.SolutionMapper;
import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Solution;
import oop.data.TextArticle;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.db.dao.SolutionDAO;
import oop.util.SessionUtils;

@Path(SolutionResource.PATH)
public class SolutionResource extends AbstractResource {

	public static final String PATH = "/solutions";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<SolutionBean> get(@PathParam("id") long id){
		Resource<Solution> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		SolutionBean bean = SolutionMapper.get().toBean(resource.getArticle());
		return new ObjectResult<SolutionBean>(bean);
	}
	
	@POST
	public ObjectResult<TextArticle> create(TextArticle text,Resource<BaseQuestion> res){
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, TextArticle.class, text);
		return new ObjectResult<TextArticle>(text);
	}
	
	@POST
	@Path("/{id: \\d+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ObjectResult<SolutionBean> update(@PathParam("id") long resourceId,
			RevisionBean<SolutionBean> data){
		WebServiceUtils.assertValid(data.getArticle() != null, "Đối tượng rỗng");
		Resource<Solution> resource = getResourceSafe(resourceId, Solution.class);
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");
		Solution solution = SolutionMapper.get().toEntity(data.getArticle());
		solution.setId(0); // coi la doi tuong moi
		SolutionDAO.persist(solution);

		saveNewRevision(resource, solution, data.getSummary(), data.isMinor());
		
		SolutionBean bean = SolutionMapper.get().toBean(solution);		
		return new ObjectResult<SolutionBean>(bean);
	}
	
}
