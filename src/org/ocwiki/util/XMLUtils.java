package org.ocwiki.util;

import org.ocwiki.conf.APIDescriptor;
import org.ocwiki.conf.ActionDescriptor;
import org.ocwiki.conf.Config;
import org.ocwiki.conf.ModuleDescriptor;
import org.ocwiki.controller.Parameter;
import org.ocwiki.util.xstream.ClassConverter;
import org.ocwiki.util.xstream.ConfigConverter;
import org.ocwiki.util.xstream.EntityConverter;

import com.thoughtworks.xstream.XStream;

public final class XMLUtils {

	private final static XStream xstream;
	
	static {
		xstream = new XStream();
		// alias configuration entries
		xstream.alias("config", Config.class);
		xstream.alias("action", ActionDescriptor.class);
		xstream.alias("api", APIDescriptor.class);
		xstream.alias("module", ModuleDescriptor.class);
		xstream.alias("param", Parameter.class);
		xstream.addImplicitCollection(ModuleDescriptor.class, "parameters",
				Parameter.class);
		xstream.addImplicitCollection(Config.class, "actionDescriptors",
				ActionDescriptor.class);
		xstream.addImplicitCollection(Config.class, "apiDescriptors",
				APIDescriptor.class);
		xstream.addImplicitCollection(Config.class, "moduleDescriptors",
				ModuleDescriptor.class);
		xstream.addImplicitCollection(ActionDescriptor.class, "requiredGroups",
				"requiredGroup", String.class);
		xstream.addImplicitCollection(APIDescriptor.class, "requiredGroups",
				"requiredGroup", String.class);
		xstream.addImplicitCollection(ModuleDescriptor.class, "requiredGroups",
				"requiredGroup", String.class);
		xstream.addImplicitCollection(ModuleDescriptor.class, "inActions",
				"inAction", String.class);
		xstream.addImplicitCollection(ModuleDescriptor.class, "articleTypes",
				"articleType", Class.class);
		xstream.aliasField("dbname", Config.class, "databaseName");
		xstream.aliasField("dbport", Config.class, "databasePort");
		xstream.aliasField("dbhost", Config.class, "databaseHost");
		xstream.aliasField("class", APIDescriptor.class, "clazz");
		xstream.aliasField("class", ActionDescriptor.class, "actionClass");
		xstream.aliasField("class", ModuleDescriptor.class, "clazz");
		// alias data entries
		xstream.alias("ans", org.ocwiki.data.Answer.class);
		xstream.alias("art", org.ocwiki.data.Article.class);
		xstream.alias("ques", org.ocwiki.data.BaseQuestion.class);
		xstream.alias("hist", org.ocwiki.data.TestAttempt.class);
		xstream.alias("lc", org.ocwiki.data.LevelConstraint.class);
		xstream.alias("sq", org.ocwiki.data.Question.class);
		xstream.alias("sect", org.ocwiki.data.Section.class);
		xstream.alias("sstr", org.ocwiki.data.SectionStructure.class);
		xstream.alias("test", org.ocwiki.data.Test.class);
		xstream.alias("tstr", org.ocwiki.data.TestStructure.class);
		xstream.alias("txt", org.ocwiki.data.Text.class);
		xstream.alias("top", org.ocwiki.data.Topic.class);
		xstream.alias("tc", org.ocwiki.data.TopicConstraint.class);
		xstream.alias("user", org.ocwiki.data.User.class);
		xstream.alias("chg", org.ocwiki.data.log.ResourceLog.class);
		// converters
		xstream.registerConverter(new EntityConverter());
		xstream.registerConverter(new ClassConverter());
		xstream.registerConverter(new ConfigConverter(xstream.getMapper()));
	}

	public static XStream getXStream() {
		return xstream;
	}
	
}
