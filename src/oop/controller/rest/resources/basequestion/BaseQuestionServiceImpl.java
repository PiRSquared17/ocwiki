package oop.controller.rest.resources.basequestion;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.AbstractResource;
import oop.controller.rest.util.ListResult;
import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Resource;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;
import oop.util.Utils;


@Path("/basequestion")
@Produces( { MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
public class BaseQuestionServiceImpl extends AbstractResource implements BaseQuestionService {

	
	
	@Override
	public BaseQuestion add(BaseQuestion question) throws Exception {
		User user = SessionUtils.getUser(getSession());
		ResourceDAO.create(user, BaseQuestion.class, question);
		return question;
	}

	@Override
	public BaseQuestion get(String qid) throws Exception {			
		long lqid = getSafeQuestionId(qid);
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle();	
		return question;
	}

	@Override
	public BaseQuestion update(String qid, BaseQuestion question) throws Exception {		
		long lqid = getSafeQuestionId(qid);
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);		
		saveNewRevision(resource, question);
		return question;			
	}

	

	@Override
	public Answer addAnswers(String qid, Answer answer) throws Exception {
		long lqid = getSafeQuestionId(qid);		
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle().copy();
		question.getAnswers().add(answer);
		saveNewRevision(resource, question);
		return answer;
	}

	@Override
	public Answer getAnswer(String qid, String aid) throws Exception {
		long lqid = getSafeQuestionId(qid);
		int iaid = getSafeAnswerId(aid);
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle().copy();					
		return question.getAnswers().get(iaid);		
	}

	@Override
	public ListResult<Answer> getAnswers(String qid) throws Exception {
		long lqid = getSafeQuestionId(qid);		
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle().copy();						
		return new ListResult<Answer>(question.getAnswers());	
	}

	@Override
	public Answer removeAnswer(String qid, String aid) throws Exception {
		long lqid = getSafeQuestionId(qid);
		int iaid = getSafeAnswerId(aid);
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle().copy();
		Answer deleted = question.getAnswers().get(iaid);
		question.getAnswers().remove(iaid);
		saveNewRevision(resource, question);
		return deleted;
	}

	@Override
	public Answer updateAnswer(String qid, String aid, Answer answer)
			throws Exception {
		long lqid = getSafeQuestionId(qid);
		int iaid = getSafeAnswerId(aid);		
		Resource<BaseQuestion> resource = getSafeQuestionResource(lqid);
		BaseQuestion question = resource.getArticle().copy();
		
		// These LoCs are stupid.
		question.getAnswers().get(iaid).setContent(answer.getContent());
		question.getAnswers().get(iaid).setCorrect(answer.isCorrect());
		
		saveNewRevision(resource, question);
		return answer;
	}

	public long getSafeQuestionId(String qid) throws Exception{
		if(!Utils.isNumberic(qid)){
			throw new Exception("The question id must be a long number.");
		}
		return Long.parseLong(qid);
	}
	
	public int getSafeAnswerId(String aid) throws Exception{
		if(!Utils.isNumberic(aid)){
			throw new Exception("The answer id must be a long number.");
		}
		return Integer.parseInt(aid);
	}
	
	public Resource<BaseQuestion> getSafeQuestionResource(long lqid){
		Resource<BaseQuestion> rbq = ResourceDAO.fetchById(lqid);
		assertResourceFound(rbq);
		BaseQuestion bq = rbq.getArticle();
		assertResourceFound(bq);
		return rbq; 			
	}
	

}
