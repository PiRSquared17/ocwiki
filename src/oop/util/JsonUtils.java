package oop.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.api.json.JSONUnmarshaller;

public final class JsonUtils {

	public static final Set<Class<?>> TYPE_SET;
	public static final Class<?>[] TYPES = {
		oop.data.Answer.class,
		oop.data.Resource.class,
		oop.data.ResourceCustomization.class,
		oop.data.ResourceReport.class,
		oop.data.ResourceSearchReport.class,
		oop.data.Revision.class,
		oop.data.BaseQuestion.class,
		oop.data.History.class,
		oop.data.LevelConstraint.class,
		oop.data.Preferences.class,
		oop.data.Question.class,
		oop.data.Section.class,
		oop.data.SectionStructure.class,
		oop.data.Test.class,
		oop.data.TestStructure.class,
		oop.data.Solution.class,
		oop.data.Text.class,
		oop.data.File.class,
		oop.data.TextArticle.class,
		oop.data.Topic.class,
		oop.data.TopicReport.class,
		oop.data.TopicConstraint.class,
		oop.data.Comment.class,
		oop.data.CommentCustomization.class,
		oop.data.CommentReport.class,
		
		oop.controller.rest.bean.AnswerBean.class,
		oop.controller.rest.bean.ArticleReferenceBean.class,
		oop.controller.rest.bean.BaseQuestionBean.class,
		oop.controller.rest.bean.CommentBean.class,
		oop.controller.rest.bean.CommentReportBean.class,
		oop.controller.rest.bean.FileBean.class,
		oop.controller.rest.bean.LevelConstraintBean.class,
		oop.controller.rest.bean.QuestionBean.class,
		oop.controller.rest.bean.ResourceBean.class,
		oop.controller.rest.bean.ResourceReferenceBean.class,
		oop.controller.rest.bean.ResourceSearchReportBean.class,
		oop.controller.rest.bean.RevisionBean.class,
		oop.controller.rest.bean.SectionBean.class,
		oop.controller.rest.bean.SolutionBean.class,
		oop.controller.rest.bean.SectionStructureBean.class,
		oop.controller.rest.bean.TextArticleBean.class,
		oop.controller.rest.bean.TextBean.class,
		oop.controller.rest.bean.TestBean.class,
		oop.controller.rest.bean.TopicBean.class,
		oop.controller.rest.bean.TopicConstraintBean.class,
		oop.controller.rest.bean.TopicReportBean.class,
		oop.controller.rest.bean.UserBean.class,
		oop.controller.rest.bean.UserReferenceBean.class,
	
		ListResult.class,
		ObjectResult.class
	};

    public static final JSONJAXBContext JAXB_CONTEXT;
    public static final JSONMarshaller MARSHALLER;
    private static final JSONUnmarshaller UNMARSHALLER;
    
	static {
		Set<Class<?>> types = new HashSet<Class<?>>();
		Collections.addAll(types, JsonUtils.TYPES);
		TYPE_SET = Collections.unmodifiableSet(types);
		
		JSONJAXBContext temp = null;
		JSONMarshaller marshaller = null;
		JSONUnmarshaller unmarshaller = null;
		try {
			temp = new JSONJAXBContext(
	                JSONConfiguration.natural().build(),
	                JsonUtils.TYPES);
			 marshaller = temp.createJSONMarshaller();
			 unmarshaller = temp.createJSONUnmarshaller();
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		JAXB_CONTEXT = temp;
		MARSHALLER = marshaller;
		UNMARSHALLER = unmarshaller;
	}

	private JsonUtils() {
	}

	public static String toJson(Object obj) throws JAXBException {
		if (obj == null) {
			return "null";
		}
		StringWriter sw = new StringWriter();
		MARSHALLER.marshallToJSON(obj, sw);
		return sw.toString();
	}
	
	public static void toJson(Object obj, Writer writer) throws JAXBException,
			IOException {
		if (obj == null) {
			writer.write("null");
		}
		MARSHALLER.marshallToJSON(obj, writer);
	}

	public static <T> T fromJson(String json, Class<T> type)
			throws JAXBException {
		StringReader sr = new StringReader(json);
		return UNMARSHALLER.unmarshalFromJSON(sr, type);
	}

}
