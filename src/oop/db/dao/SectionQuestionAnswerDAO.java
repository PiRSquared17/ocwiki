package oop.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import oop.db.Database;

public class SectionQuestionAnswerDAO {

	public static void addAnswers(long sectionId, long questionId, int count)
			throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO tblSectionQuestionAnswer (sect_id, ques_id, ans_id) " +
					"SELECT ?, ques_id, tblAnswer.ans_id " +
					"FROM tblQuestionAnswer, tblAnswer " +
					"WHERE tblQuestionAnswer.ans_id = tblAnswer.ans_id " +
					"AND ques_id = ? " +
					"ORDER BY ans_priority DESC " +
					"LIMIT 0, ?";
	
			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, sectionId);
			stmt.setLong(2, questionId);
			stmt.setInt(3, count);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static void removeAnswers(long sectionId, long questionId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM tblSectionQuestionAnswer " +
					"WHERE sect_id=? AND ques_id=?";
			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, sectionId);
			stmt.setLong(2, questionId);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static Set<Long> getUsedAnswerIds(long sectionId, long questionId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT ans_id " +
					"FROM tblSectionQuestionAnswer " +
					"WHERE sect_id=? AND ques_id=?";
			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, sectionId);
			stmt.setLong(2, questionId);
			rs = stmt.executeQuery();

			Set<Long> ids = new HashSet<Long>();
			while (rs.next()) {
				ids.add(rs.getLong(1));
			}
			return ids;
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
	public static void addAnswer(long sectionId, long questionId, long answerId)
		throws SQLException {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				String sql = "INSERT INTO tblSectionQuestionAnswer " +
						"(sect_id, ques_id, ans_id) VALUES (?, ?, ?)";
		
				conn = Database.get().getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, sectionId);
				stmt.setLong(2, questionId);
				stmt.setLong(3, answerId);
				stmt.executeUpdate();
			} finally {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
	}
	
	public static void removeAnswer(long sectionId, long questionId, long answerId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM tblSectionQuestionAnswer " +
					"WHERE sect_id=? AND ques_id=? AND  ans_id=?";
			conn = Database.get().getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, sectionId);
			stmt.setLong(2, questionId);
			stmt.setLong(3, answerId);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
	
}
