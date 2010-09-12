package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ObjectResult;
import oop.data.Comment;
import oop.data.CommentCustomization;
import oop.db.dao.CommentCustomizationDAO;
import oop.db.dao.CommentDAO;

@Path("/CommentCustomization_customizations")
public class CommentCustomizationResource extends AbstractResource {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ObjectResult<CommentCustomization> create(CommentCustomization data) {
		Comment comment = CommentDAO.fetch(data.getComment().getId());
		CommentCustomization customization = new CommentCustomization(comment,
				getUser(), data.getStatus());
		CommentCustomizationDAO.persist(customization);
		return new ObjectResult<CommentCustomization>(customization);
	}

}
