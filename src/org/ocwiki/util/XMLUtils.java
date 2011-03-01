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
		xstream.addImplicitCollection(Config.class, "listenerClasses",
				"listener", Class.class);
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
		// converters
		xstream.registerConverter(new EntityConverter());
		xstream.registerConverter(new ClassConverter());
		xstream.registerConverter(new ConfigConverter(xstream.getMapper()));
	}

	public static XStream getXStream() {
		return xstream;
	}
	
}
