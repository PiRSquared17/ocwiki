package oop.controller.action.solution;

import oop.controller.action.AbstractAction;
import oop.controller.action.ActionException;
import oop.data.BaseQuestion;
import oop.data.Namespace;
import oop.data.Resource;
import oop.data.Solution;
import oop.data.Text;
import oop.db.dao.BaseQuestionDAO;
import oop.db.dao.ResourceDAO;

public class CreateSolution extends AbstractAction {

	Resource<BaseQuestion> resource;
	BaseQuestion basequestion;
	
	@Override
	protected void performImpl() throws Exception {
		resource = BaseQuestionDAO.fetchById(getParams().getLong("id"));
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
	
	public Resource<BaseQuestion> getResource(){
		return resource;
	}
	
	public BaseQuestion getQuestion(){
		return basequestion;
	}

}
