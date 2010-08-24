package oop.controller.rest.resources.basequestion;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.data.Answer;
import oop.data.BaseQuestion;

@Path("/basequestion")
@Produces( { MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
public interface BaseQuestionService {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public BaseQuestion add(BaseQuestion question) throws Exception;
	
	@GET
	@Path("/{basequestionid}")
	public BaseQuestion get(@PathParam("basequestionid") String qid) throws Exception;
			
	@POST
	@Path("/{basequestionid}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public BaseQuestion update(@PathParam("basequestionid") String qid,BaseQuestion question) throws Exception;
	
	@GET
	@Path("/{basequestionid}/answers")
	public ListResult<Answer> getAnswers(@PathParam("basequestionid") String qid) throws Exception;
	
	@POST
	@Path("/{basequestionid}/answers/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Answer addAnswers(@PathParam("basequestionid") String qid, Answer answer) throws Exception;
	
	@GET
	@Path("/{basequestionid}/answers/{answerid}")
	public Answer getAnswer(@PathParam("basequestionid") String qid, @PathParam("answerid") String aid) throws Exception;
	
	@POST
	@Path("/{basequestionid}/answers/{answerid}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Answer updateAnswer(@PathParam("basequestionid") String qid, @PathParam("answerid") String aid, Answer answer) throws Exception;
	
	@DELETE
	@Path("/{basequestionid}/answers/{answerid}")	
	public Answer removeAnswer(@PathParam("basequestionid") String qid, @PathParam("answerid") String aid) throws Exception;
		
	
	


}
