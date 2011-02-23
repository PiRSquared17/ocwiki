package org.ocwiki.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.ocwiki.controller.rest.bean.RevisionBean;
import org.ocwiki.controller.rest.bean.SolutionBean;
import org.ocwiki.controller.rest.bean.SolutionMapper;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.BaseQuestion;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.data.TextArticle;
import org.ocwiki.data.User;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.SolutionDAO;
import org.ocwiki.util.SessionUtils;

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
