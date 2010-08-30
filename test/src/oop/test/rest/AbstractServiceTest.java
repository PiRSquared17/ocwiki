package oop.test.rest;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.BeforeClass;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AbstractServiceTest {

	public AbstractServiceTest() {
		super();
	}
	
	@BeforeClass
	public static void setupClass() {
		Config config = new Config();
		ConfigIO.loadDirectory(config, "WebContent/WEB-INF/conf");
		HibernateUtil.init(config);
		Config.setDefaultInstance(config);
	}

	protected Client createClient() {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JacksonJaxbJsonProvider.class);
		Client client = Client.create(config);
		return client;
	}
	
	protected WebResource createResource(String path) {
		Client client = createClient();
		return client .resource(Config.get().getRestPath() + path);
	}

}