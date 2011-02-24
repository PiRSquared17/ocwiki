package org.ocwiki.controller.action.solution;

import java.io.IOException;

import javax.servlet.ServletException;

import org.ocwiki.controller.action.AbstractAction;
import org.ocwiki.controller.action.ActionException;
import org.ocwiki.data.MultichoiceQuestion;
import org.ocwiki.data.Namespace;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Solution;
import org.ocwiki.data.Text;
import org.ocwiki.db.dao.MultichoiceQuestionDAO;
import org.ocwiki.db.dao.ResourceDAO;

public class CreateSolution extends AbstractAction {

	Resource<MultichoiceQuestion> resource;
	MultichoiceQuestion basequestion;
	
	@Override
	protected void performImpl() throws IOException, ServletException {
		resource = MultichoiceQuestionDAO.fetchById(getParams().getLong("id"));
		if(resource==null){
			throw new ActionException("Không tìm thấy câu hỏi!");
		}
		basequestion = resource.getArticle();
		if(!getParams().hasParameter("submit")){
			return;
		}
		if(getUser()==null){
			throw new ActionException("Bạn cần đăng nhập để thực hiện chức năng này");
		}
		String submit=getParams().getString("submit");
		if("create".equals(submit)){
			String name = getParams().getString("name");
			String content = getParams().getString("bai_giai");
			Namespace namespace = resource.getNamespace();
			Text text = new Text(content);
			Solution solution = new Solution(name, namespace, text, resource);
			//Resource<TextArticle> res = ResourceDAO.create(getUser(), TextArticle.class, textaritcle);
			Resource<Solution> res=ResourceDAO.create(getUser(), Solution.class, solution, resource);
			setNextAction("solution.view?id="+res.getId());
		}
		
	}
	
	public Resource<MultichoiceQuestion> getResource(){
		return resource;
	}
	
	public MultichoiceQuestion getQuestion(){
		return basequestion;
	}

}
