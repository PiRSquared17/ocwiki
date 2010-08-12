package oop.db.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oop.data.SectionStructure;
import oop.data.Status;
import oop.data.TestStructure;
import oop.data.Text;
import oop.db.Database;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SectionStructureDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static SectionStructure fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (SectionStructure) session.get(SectionStructure.class, id);
	}

	@Deprecated
	public static void persist(SectionStructure section) {
	}

	public static SectionStructure create(String contentStr, long testStructureId,
			int order) throws SQLException {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Text content = contentStr == null ? null : new Text(contentStr);
			TestStructure testStructure = (TestStructure) session.load(
					TestStructure.class, testStructureId);
			SectionStructure sectionStructure = new SectionStructure(
					testStructure, order, content);
			
			tx = session.beginTransaction();
			session.save(content);
			session.save(sectionStructure);
			tx.commit();
			session.refresh(testStructure);
			return sectionStructure;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static void drop(long sectionId, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			SectionStructure sectionStructure = (SectionStructure) session
					.load(SectionStructure.class, sectionId);
			tx = session.beginTransaction();
			sectionStructure.setStatus(Status.DELETED);
			tx.commit();
			session.refresh(sectionStructure.getTestStructure());
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static void shiftDown(long testId, long order) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE tblSectionStructure " +
					"SET sstr_order = sstr_order + 1 " +
					"WHERE sstr_test = ? AND sstr_order >= ?";

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
			stmt = conn.prepareCall("{call spSectionStructureNormalize(?)}");
			stmt.setLong(1, testId);
			stmt.execute();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static void moveUp(long id) throws SQLException {
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = Database.get().getConnection();
			stmt = conn.prepareCall("{call spSectionStructureMoveUp(?)}");
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
			stmt = conn.prepareCall("{call spSectionStructureMoveDown(?)}");
			stmt.setLong(1, id);
			stmt.execute();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}		
	}
	
}
