package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("unchecked")
public class JSONContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
	private Class[] types = {
			oop.data.Resource.class,
			oop.data.ResourceSearchReport.class,
			oop.data.Revision.class,
    		oop.data.Answer.class,
    		oop.data.BaseQuestion.class,
    		oop.data.History.class,
    		oop.data.LevelConstraint.class,
    		oop.data.Preferences.class,
    		oop.data.Question.class,
    		oop.data.Section.class,
    		oop.data.SectionStructure.class,
    		oop.data.Test.class,
    		oop.data.TestStructure.class,
    		oop.data.Text.class,
    		oop.data.TextArticle.class,
    		oop.data.Topic.class,
    		oop.data.TopicConstraint.class,
    		oop.data.Comment.class,
    		oop.data.CommentCustomization.class,
    		oop.data.CommentReport.class,
    		oop.controller.rest.bean.ResourceBean.class,
			oop.controller.rest.bean.ResourceReferenceBean.class,
			oop.controller.rest.bean.RevisionBean.class,
			oop.controller.rest.bean.ArticleReferenceBean.class,
//    		oop.controller.rest.bean.AnswerBean.class,
    		oop.controller.rest.bean.BaseQuestionBean.class,
//    		oop.controller.rest.bean.History.class,
//    		oop.controller.rest.bean.LevelConstraint.class,
//    		oop.controller.rest.bean.Preferences.class,
    		oop.controller.rest.bean.QuestionBean.class,
    		oop.controller.rest.bean.SectionBean.class,
//    		oop.controller.rest.bean.SectionStructure.class,
    		oop.controller.rest.bean.TestBean.class,
//    		oop.controller.rest.bean.TestStructure.class,
//    		oop.controller.rest.bean.Text.class,
    		oop.controller.rest.bean.TextArticleBean.class,
    		oop.controller.rest.bean.TopicBean.class,
//    		oop.controller.rest.bean.TopicConstraint.class,
    		oop.controller.rest.bean.CommentBean.class,
//    		oop.controller.rest.bean.CommentCustomization.class,
    		oop.controller.rest.bean.CommentReportBean.class,
    		ListResult.class,
    		ObjectResult.class,
    };

    public JSONContextResolver() throws Exception {
        this.context = new JSONJAXBContext(
                JSONConfiguration.natural().build(),
                types);
    }

	public JAXBContext getContext(Class<?> objectType) {
		for (Class type : types) {
			if (type.isAssignableFrom(objectType)) {
				return context;
			}
		}
		return null;
	}
}

