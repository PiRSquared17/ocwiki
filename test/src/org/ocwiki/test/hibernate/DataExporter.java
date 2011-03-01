package org.ocwiki.test.hibernate;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.ocwiki.conf.Config;
import org.ocwiki.conf.ConfigIO;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

public class DataExporter {

	public static void main(String[] args) throws Exception {
		Config config = new Config();
		ConfigIO.load("WebContent/WEB-INF/conf", config);

		// database connection
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + config.getDatabaseHost() + ":"
				+ config.getDatabasePort() + "/" + config.getDatabaseName()
				+ "?useUnicode=true&characterEncoding=UTF-8";
		Connection jdbcConnection = DriverManager.getConnection(url, config
				.getUserName(), config.getPassword());
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		XmlDataSet.write(fullDataSet, new FileOutputStream(
				"test/dataset/full.xml"));

//		// export articles
//		// dependent tables database export: export table X and all tables that
//		// have a PK which is a FK on X, in the right order for insertion
//		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(
//				connection, config.getTablePrefix() + "Article");
//		IDataSet depDataset = connection.createDataSet(depTableNames);
//		FlatXmlDataSet.write(depDataset, new FileOutputStream(
//				"test/dataset/article.xml"));

		// export user table
		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
//		String[] depTableNames = new String[] {"ocwUser"};
//		IDataSet depDataset = connection.createDataSet(depTableNames);
//		FlatXmlDataSet.write(depDataset, new FileOutputStream(
//				"test/dataset/user.xml"));
	}

}
