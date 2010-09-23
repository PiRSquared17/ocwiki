package oop.controller.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentCustomization;
import oop.data.CommentReport;
import oop.db.dao.CommentCustomizationDAO;
import oop.db.dao.CommentDAO;
import oop.db.dao.CommentReportDAO;

@Path("/comment_customizations")
public class CommentCustomizationResource extends AbstractResource {

	@POST
	public ObjectResult<CommentReport> create(CommentCustomization data) {
		Comment comment = CommentDAO.fetch(data.getComment().getId());
		CommentCustomization customization = new CommentCustomization(comment,
				getUser(), data.getStatus());
		CommentCustomizationDAO.persist(customization);
		
		//Hacon
		CommentReport returnCommentReport = CommentReportDAO.fetch(data.getComment().getId(), getUser());
		
		return new ObjectResult<CommentReport>(returnCommentReport);
	}

}
