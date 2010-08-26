package oop.controller.rest;

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
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("unchecked")
public class JSONContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
	private Class[] types = {
			oop.data.Resource.class,
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
    		ListResult.class,
    		ObjectResult.class,
    };

    public JSONContextResolver() throws Exception {
        this.context = new JSONJAXBContext(
                JSONConfiguration.natural().build(),
                types);
    }

	public JAXBContext getContext(Class<?> objectType) {
		return (objectType == ListResult.class || 
				objectType == ObjectResult.class) ? context
				: null;
	}
}

