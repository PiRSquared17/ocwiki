package oop.db.dao;

import oop.data.Answer;
import oop.data.BaseQuestion;
import oop.data.Status;
import oop.data.Text;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public final class AnswerDAO {

	public static Answer fetch(long id) {
		Session session = HibernateUtil.getSession();
		return (Answer) session.get(Answer.class, id);
	}
	
	public static Answer create(long baseQuestionId, String contentStr,
			boolean correct) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			BaseQuestion baseQuestion = (BaseQuestion) session.load(
					BaseQuestion.class, baseQuestionId);
			Text content = new Text(contentStr);
			Answer answer = new Answer(baseQuestion, content, correct);
			tx = session.beginTransaction();
			session.save(content);
			session.save(answer);
			tx.commit();
			session.refresh(baseQuestion);
			return answer;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	@Deprecated
	public static void persist(Answer answer) {
	}
	
	public static void delete(long answerId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Answer answer = (Answer) session.load(Answer.class, answerId);
			answer.setStatus(Status.DELETED);
			tx.commit();
			session.refresh(answer.getQuestion());
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
}
