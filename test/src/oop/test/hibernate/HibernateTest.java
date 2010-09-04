package oop.test.hibernate;


import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class HibernateTest {

	private String datasetFile;

	public HibernateTest() {
	}

	public HibernateTest(String datasetFile) {
		super();
		this.datasetFile = datasetFile;
	}

	/**
	 * Khởi tạo cấu hình và tất cả các bảng
	 * @throws Exception
	 */
	@BeforeClass
	public static void setupClass() throws Exception {
		Config config = new Config();
		ConfigIO.loadDirectory(config, "test/conf");
		HibernateUtil.init(config);
		Config.setDefaultInstance(config);
		// clean insert all tables
		HibernateTestUtil.cleanInsertDataset("test/dataset/full.xml");
	}

	/**
	 * Khởi tạo lại bảng được test case yêu cầu
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		if (datasetFile != null) {
			HibernateTestUtil.cleanInsertDataset("test/dataset/" + datasetFile);
		}
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
	}

}
