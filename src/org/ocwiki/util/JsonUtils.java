package org.ocwiki.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.ocwiki.controller.rest.util.ListResult;
import org.ocwiki.controller.rest.util.ObjectResult;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.api.json.JSONUnmarshaller;

public final class JsonUtils {

	public static final Set<Class<?>> TYPE_SET;
	public static final Class<?>[] TYPES = {
		org.ocwiki.data.Answer.class,
		org.ocwiki.data.Resource.class,
		org.ocwiki.data.ResourceCustomization.class,
		org.ocwiki.data.ResourceReport.class,
		org.ocwiki.data.ResourceSearchReport.class,
		org.ocwiki.data.Revision.class,
		org.ocwiki.data.BaseQuestion.class,
		org.ocwiki.data.TestAttempt.class,
		org.ocwiki.data.LevelConstraint.class,
		org.ocwiki.data.Preferences.class,
		org.ocwiki.data.Question.class,
		org.ocwiki.data.Section.class,
		org.ocwiki.data.SectionStructure.class,
		org.ocwiki.data.Test.class,
		org.ocwiki.data.TestStructure.class,
		org.ocwiki.data.Solution.class,
		org.ocwiki.data.Text.class,
		org.ocwiki.data.File.class,
		org.ocwiki.data.TextArticle.class,
		org.ocwiki.data.Topic.class,
		org.ocwiki.data.TopicReport.class,
		org.ocwiki.data.TopicConstraint.class,
		org.ocwiki.data.Comment.class,
		org.ocwiki.data.CommentCustomization.class,
		org.ocwiki.data.CommentReport.class,
		
		org.ocwiki.controller.rest.bean.AnswerBean.class,
		org.ocwiki.controller.rest.bean.ArticleReferenceBean.class,
		org.ocwiki.controller.rest.bean.BaseQuestionBean.class,
		org.ocwiki.controller.rest.bean.CommentBean.class,
		org.ocwiki.controller.rest.bean.CommentReportBean.class,
		org.ocwiki.controller.rest.bean.FileBean.class,
		org.ocwiki.controller.rest.bean.LevelConstraintBean.class,
		org.ocwiki.controller.rest.bean.QuestionBean.class,
		org.ocwiki.controller.rest.bean.ResourceBean.class,
		org.ocwiki.controller.rest.bean.ResourceReferenceBean.class,
		org.ocwiki.controller.rest.bean.ResourceSearchReportBean.class,
		org.ocwiki.controller.rest.bean.RevisionBean.class,
		org.ocwiki.controller.rest.bean.SectionBean.class,
		org.ocwiki.controller.rest.bean.SolutionBean.class,
		org.ocwiki.controller.rest.bean.SectionStructureBean.class,
		org.ocwiki.controller.rest.bean.TextArticleBean.class,
		org.ocwiki.controller.rest.bean.TextBean.class,
		org.ocwiki.controller.rest.bean.TestBean.class,
		org.ocwiki.controller.rest.bean.TopicBean.class,
		org.ocwiki.controller.rest.bean.TopicConstraintBean.class,
		org.ocwiki.controller.rest.bean.TopicReportBean.class,
		org.ocwiki.controller.rest.bean.UserBean.class,
		org.ocwiki.controller.rest.bean.UserReferenceBean.class,
		org.ocwiki.controller.rest.bean.ResourceCustomizationBean.class,
		org.ocwiki.controller.rest.bean.ResourceReportBean.class,
	
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
