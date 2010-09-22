package oop.controller.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentCustomization;
import oop.db.dao.CommentCustomizationDAO;
import oop.db.dao.CommentDAO;

@Path("/comment_customizations")
public class CommentCustomizationResource extends AbstractResource {

	@POST
	public ObjectResult<CommentCustomization> create(CommentCustomization data) {
		Comment comment = CommentDAO.fetch(data.getComment().getId());
		CommentCustomization customization = new CommentCustomization(comment,
				getUser(), data.getStatus());
		CommentCustomizationDAO.persist(customization);
		return new ObjectResult<CommentCustomization>(customization);
	}

}
