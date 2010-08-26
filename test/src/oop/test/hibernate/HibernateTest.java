package oop.test.hibernate;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class HibernateTest {

	private static Config config;
	private String datasetFile;

	static {
		config = new Config();
		ConfigIO.loadDirectory(config, "test/conf");
		HibernateUtil.init(config);
	}
	
	public HibernateTest() {
	}
	
	public HibernateTest(String datasetFile) {
		super();
		this.datasetFile = datasetFile;
	}

	@BeforeClass
	public static void _initAll() throws Exception {
		// truncate all tables
		IDatabaseConnection dbconn = createDbconn();
		IDataSet all = readDataSet("test/dataset/full.xml");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbconn, all);
	}

	private static IDatabaseConnection createDbconn()
			throws ClassNotFoundException, SQLException, DatabaseUnitException {
		IDatabaseConnection dbconn = new MySqlConnection(HibernateUtil
				.getSession().connection(), "ocwiki_unittest");
		return dbconn;
	}

	private static IDataSet readDataSet(String filename)
			throws DataSetException, IOException {
		FileReader reader = null;
		try {
			reader = new FileReader(filename);
			return new FlatXmlDataSetBuilder().build(reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	@Before
	public void _init() throws Exception {
		if (datasetFile != null) {
			IDatabaseConnection dbconn = createDbconn();
			IDataSet dataset = readDataSet("test/dataset/" + datasetFile);
			DatabaseOperation.CLEAN_INSERT.execute(dbconn, dataset);
		}
	}

	@After
	public void _clean() throws Exception {
		HibernateUtil.closeSession();
	}

}
