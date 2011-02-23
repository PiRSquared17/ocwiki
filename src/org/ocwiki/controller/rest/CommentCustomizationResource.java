package org.ocwiki.controller.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.ocwiki.controller.rest.util.ObjectResult;
import org.ocwiki.data.Comment;
import org.ocwiki.data.CommentCustomization;
import org.ocwiki.data.CommentReport;
import org.ocwiki.db.dao.CommentCustomizationDAO;
import org.ocwiki.db.dao.CommentDAO;
import org.ocwiki.db.dao.CommentReportDAO;

@Path(CommentCustomizationResource.PATH)
public class CommentCustomizationResource extends AbstractResource {

	public static final String PATH = "/comment_customizations";
	
	@POST
	public ObjectResult<CommentReport> create(CommentCustomization data) {
		Comment comment = CommentDAO.fetch(data.getComment().getId());
		CommentCustomization customization = new CommentCustomization(comment,
				getUserNullSafe(), data.getStatus());
		CommentCustomizationDAO.persist(customization);
		
		//Hacon
		CommentReport returnCommentReport = CommentReportDAO.fetch(data.getComment().getId(), getUser());
		
		return new ObjectResult<CommentReport>(returnCommentReport);
	}

}
