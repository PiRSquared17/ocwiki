package oop.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oop.conf.Config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

/**
 * use Hibernate instead!
 * @author cumeo89
 */
@Deprecated
public class Database {

	private static final Database INSTANCE = new Database();

	public static Database get() {
		return INSTANCE;
	}

	private MysqlConnectionPoolDataSource dataSource = null;

	public Database() {
		try {
			Config config = Config.get();

			Class.forName("com.mysql.jdbc.Driver");
			dataSource = new MysqlConnectionPoolDataSource();
			dataSource.setCharacterEncoding("UTF-8");
			dataSource
					.setUrl("jdbc:mysql://" + config.getDatabaseHost() + ":"
							+ config.getDatabasePort() + "/"
							+ config.getDatabaseName());
			dataSource.setUser(config.getUserName());
			dataSource.setPassword(config.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public long count(String sql) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}