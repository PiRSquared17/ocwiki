package oop.controller.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import oop.controller.rest.util.ObjectResult;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

@Path(TopicResource.PATH)
public class TopicResource extends AbstractResource {
	public static final String PATH = "/Topics";
	
	@GET
	@Path("/{id: \\d+}")
	public ObjectResult<Topic> get(@PathParam("id") long id){
		Topic topic = TopicDAO.fetchById(id).getArticle();
		return new ObjectResult<Topic>(topic);
	}
}
