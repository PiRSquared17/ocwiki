package org.ocwiki.test.rest;

import java.io.IOException;

import org.ocwiki.conf.Config;
import org.ocwiki.controller.OcwikiApp;

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
	public static void setupClass() throws IOException {
		OcwikiApp.initialize("WebContent/WEB-INF/conf");
	}

	protected Client createClient() {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JacksonJaxbJsonProvider.class);
		Client client = Client.create(config);
		return client;
	}
	
	protected WebResource createResource(String path) {
		Client client = createClient();
		return client.resource(Config.get().getRestPath() + path);
	}

}