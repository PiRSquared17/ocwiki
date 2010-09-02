package oop.test.rest.servletunit;

import java.io.File;
import java.io.IOException;

import oop.conf.Config;
import oop.persistence.HibernateUtil;
import oop.test.hibernate.HibernateTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import com.meterware.servletunit.ServletRunner;

public class ServiceTest {

	private static ServletRunner servletRunner;

	@BeforeClass
	public static void setupClass() throws IOException, SAXException {
		String contextPath = "/ocwiki";
		servletRunner = new ServletRunner(new File("test/web.xml"), contextPath);
		Config.get().setHomeDir("http://localhost/" + contextPath);
	}

	@Before
	public void setup() throws Exception {
		HibernateTestUtil.cleanInsertDataset("test/dataset/full.xml");
	}

	@After
	public void tearDown() {
		HibernateUtil.closeSession();
	}

	public ServiceTest() {
		super();
	}

	protected static ServletRunner getServletRunner() {
		return servletRunner;
	}

}