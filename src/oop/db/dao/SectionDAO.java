package oop.db.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oop.data.Article;
import oop.data.Section;
import oop.data.Status;
import oop.data.Test;
import oop.data.Text;
import oop.db.Database;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SectionDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Section fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (Section) session.get(Section.class, id);
	}
	
	public static Section create(String textStr, int order, long testId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Text text = new Text(textStr);
			Article test = (Article) session.load(Test.class, testId);
			Section section = new Section(text, order, test);

			tx = session.beginTransaction();
			session.save(text);
			session.save(section);
			tx.commit();

			session.refresh(test);
			
			return section;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	public static void setDeleted(long sectionId, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Section section = (Section) session.load(Section.class, sectionId);
			tx = session.beginTransaction();
			section.setStatus(Status.DELETED);
			tx.commit();
			session.refresh(section.getTest());
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	@Deprecated
	public static void persist(Section section) throws SQLException {
	}

	public static void shiftDown(long testId, long order) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE tblSection " +
					"SET sect_order = sect_order + 1 " +
					"WHERE sect_test = ? AND sect_order >= ?";

			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, testId);
			stmt.setLong(2, order);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}		
	}

	public static void normalize(long testId) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = Database.get().getConnection();
			stmt = conn.prepareCall("{call spSectionNormalize(?)}");
			stmt.setLong(1, testId);
			stmt.execute();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static int nextAvailableOrder(long testId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT MAX(sect_order) "
					+ "FROM tblSection " 
					+ "WHERE sect_test = ? " 
					+ "GROUP BY sect_test";
	
			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, testId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static void moveUp(long id) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = Database.get().getConnection();
			stmt = conn.prepareCall("{call spSectionMoveUp(?)}");
			stmt.setLong(1, id);
			stmt.execute();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}		
	}
	
	public static void moveDown(long id) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = Database.get().getConnection();
			stmt = conn.prepareCall("{call spSectionMoveDown(?)}");
			stmt.setLong(1, id);
			stmt.execute();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}		
	}
	
}
