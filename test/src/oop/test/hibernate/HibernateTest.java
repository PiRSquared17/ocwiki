package oop.test.hibernate;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oop.conf.Config;
import oop.conf.ConfigIO;
import oop.persistence.HibernateUtil;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class HibernateTest {

	private static Config config;
	private String datasetFile;

	public HibernateTest() {
	}
	
	public HibernateTest(String datasetFile) {
		super();
		this.datasetFile = datasetFile;
	}

	@BeforeClass
	public static void _initAll() throws Exception {
		config = new Config();
		ConfigIO.loadDirectory(config, "test/conf");
		HibernateUtil.init(config);

		// truncate all tables
		IDatabaseConnection dbconn = createDbconn();
		IDataSet all = readDataSet("test/dataset/all.xml");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbconn, all);
		dbconn.close();
	}

	private static IDatabaseConnection createDbconn()
			throws ClassNotFoundException, SQLException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + config.getDatabaseHost() + ":"
				+ config.getDatabasePort() + "/" + config.getDatabaseName()
				+ "?useUnicode=true&characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(url, config
				.getUserName(), config.getPassword());
		IDatabaseConnection dbconn = new MySqlConnection(conn,
				"ocwiki_unittest");
		return dbconn;
	}

	private static XmlDataSet readDataSet(String filename)
			throws DataSetException, IOException {
		FileReader reader = null;
		try {
			reader = new FileReader(filename);
			return new XmlDataSet(reader);
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
			dbconn.close();
		}
	}

	@After
	public void _clean() throws Exception {
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
		HibernateUtil.closeSession();
	}

}
