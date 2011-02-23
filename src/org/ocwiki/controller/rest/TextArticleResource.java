package org.ocwiki.controller.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.ocwiki.controller.rest.bean.RevisionBean;
import org.ocwiki.controller.rest.bean.TextArticleBean;
import org.ocwiki.controller.rest.bean.TextArticleMapper;
import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.Resource;
import org.ocwiki.data.ResourceSearchReport;
import org.ocwiki.data.TextArticle;
import org.ocwiki.db.dao.ArticleDAO;
import org.ocwiki.db.dao.ResourceDAO;
import org.ocwiki.db.dao.TextArticleDAO;

@Path(TextArticleResource.PATH)
public class TextArticleResource extends AbstractResource {
	
	public static final String PATH = "/texts";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticleBean> get(@PathParam("id") long id){
		Resource<TextArticle> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		TextArticleBean bean = TextArticleMapper.get().toBean(resource.getArticle());
		return new ObjectResult<TextArticleBean>(bean);		
	}
	
	@GET
	@Path("/related/{resourceID: \\d+}")
	public ListResult<ResourceSearchReport<TextArticle>> listByRelatedResource(
			@PathParam("resourceID") long resourceID) {
		List<ResourceSearchReport<TextArticle>> listRelatedTest = ArticleDAO
				.fetchRelated(TextArticle.class, resourceID, 0, 5);
		return new ListResult<ResourceSearchReport<TextArticle>>(listRelatedTest);
	}
	
	@POST
	@Path("/{id: \\d+}")
	public ObjectResult<TextArticleBean> update(
			@PathParam("id") long id,
			RevisionBean<TextArticleBean> data){
		WebServiceUtils.assertValid(data.getArticle() != null, "Đối tượng rỗng");
		Resource<TextArticle> resource = getResourceSafe(id, TextArticle.class);
		WebServiceUtils.assertValid(resource.getArticle().getId() == data
				.getArticle().getId(), "old version");
		TextArticle textarticle = TextArticleMapper.get().toEntity(data.getArticle());
		textarticle.setId(0); // coi la doi tuong moi
		
		TextArticleDAO.persist(textarticle);
		saveNewRevision(resource, textarticle,data.getSummary(),data.isMinor());
		
		TextArticleBean bean = TextArticleMapper.get().toBean(textarticle);
		return new ObjectResult<TextArticleBean>(bean);
	}
}
