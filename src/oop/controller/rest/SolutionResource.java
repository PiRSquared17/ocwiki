package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.Solution;
import oop.data.TextArticle;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.db.dao.SolutionDAO;
import oop.util.SessionUtils;

@Path(SolutionResource.PATH)
public class SolutionResource extends AbstractResource {

	public static final String PATH = "/Solution";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Solution> get(@PathParam("id") long id){
		Resource<Solution> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		return new ObjectResult<Solution>(resource.getArticle());
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
	public ObjectResult<Solution> update(@PathParam("id") long resourceId,
			Revision<Solution> data){
		WebServiceUtils.assertValid(data.getArticle() != null, "Đối tượng rỗng");
		Resource<Solution> resource = getResourceSafe(resourceId, Solution.class);
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");
		Solution solution = data.getArticle();
		SolutionDAO.persist(solution);

		saveNewRevision(resource, solution, data.getSummary(), data.isMinor());
		
		return new ObjectResult<Solution>(solution);
	}
	
}
