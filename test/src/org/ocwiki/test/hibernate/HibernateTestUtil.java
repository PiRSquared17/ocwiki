package org.ocwiki.test.hibernate;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.ocwiki.conf.Config;
import org.ocwiki.persistence.HibernateUtil;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;

public class HibernateTestUtil {

	public static void cleanInsertDataset(String filename) throws ClassNotFoundException,
			SQLException, DatabaseUnitException, DataSetException, IOException {
		IDatabaseConnection dbconn = HibernateTestUtil.createDbconn();
		HibernateTestUtil.setConstraintCheck(dbconn, false);
		IDataSet all = HibernateTestUtil.readDataSet(filename);
		DatabaseOperation.CLEAN_INSERT.execute(dbconn, all);
		HibernateTestUtil.setConstraintCheck(dbconn, true);
		dbconn.getConnection().commit();
	}

	private static IDatabaseConnection createDbconn()
			throws ClassNotFoundException, SQLException, DatabaseUnitException {
		Connection connection = HibernateUtil.getSession().connection();
		IDatabaseConnection dbconn = new MySqlConnection(connection, Config
				.get().getDatabaseName());
		return dbconn;
	}

	private static void setConstraintCheck(IDatabaseConnection dbconn, boolean on) throws SQLException {
		Connection connection = dbconn.getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("SET @OCW_TRIGGER_DISABLED = " + (on ? "0" : "1") + ";");
		stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = " + (on ? "1" : "0") + ";");
	}

	private static IDataSet readDataSet(String filename)
			throws DataSetException, IOException {
		FileReader reader = null;
		try {
			reader = new FileReader(filename);
			return new XmlDataSet(reader);
			// return new XmlDataSet(reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}
