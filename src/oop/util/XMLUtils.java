package oop.util;

import oop.conf.APIDescriptor;
import oop.conf.ActionDescriptor;
import oop.conf.Config;
import oop.conf.ModuleDescriptor;
import oop.util.xstream.ClassConverter;
import oop.util.xstream.ConfigConverter;
import oop.util.xstream.EntityConverter;

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
		xstream.alias("ans", oop.data.Answer.class);
		xstream.alias("art", oop.data.Article.class);
		xstream.alias("ques", oop.data.BaseQuestion.class);
		xstream.alias("hist", oop.data.History.class);
		xstream.alias("lc", oop.data.LevelConstraint.class);
		xstream.alias("sq", oop.data.Question.class);
		xstream.alias("sect", oop.data.Section.class);
		xstream.alias("sstr", oop.data.SectionStructure.class);
		xstream.alias("test", oop.data.Test.class);
		xstream.alias("tstr", oop.data.TestStructure.class);
		xstream.alias("txt", oop.data.Text.class);
		xstream.alias("top", oop.data.Topic.class);
		xstream.alias("tc", oop.data.TopicConstraint.class);
		xstream.alias("user", oop.data.User.class);
		xstream.alias("chg", oop.data.log.ResourceLog.class);
		// converters
		xstream.registerConverter(new EntityConverter());
		xstream.registerConverter(new ClassConverter());
		xstream.registerConverter(new ConfigConverter(xstream.getMapper()));
	}

	public static XStream getXStream() {
		return xstream;
	}
	
}
