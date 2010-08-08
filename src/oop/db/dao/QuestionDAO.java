package oop.db.dao;

import java.util.List;

import oop.data.BaseQuestion;
import oop.data.Question;
import oop.data.Section;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
public class QuestionDAO {

	/**
	 * Lấy dữ liệu từ DB, nếu không tìm thấy trả về null. 
	 * @param id
	 * @return
	 */
	public static Question fetchById(long id) {
		Session session = HibernateUtil.getSession();
		return (Question) session.get(Question.class, id);
	}
	
	public static Question fetchBySectionQuestion(long sectionId, long baseQuestionId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Question where section=:section and base=:base";
		Query query = session.createQuery(hql);
		query.setEntity("section", session.load(Section.class, sectionId));
		query.setEntity("base", session.load(BaseQuestion.class, baseQuestionId));
		return (Question) query.uniqueResult();
	}

	public static List<Question> fetchBySection(long sectionId) {
		Session session = HibernateUtil.getSession();
		String hql = "from Question where section=:section";
		Query query = session.createQuery(hql);
		query.setEntity("section", session.load(Section.class, sectionId));
		return query.list();
	}
	
	public static Question create(long sectionId, long baseId, int mark) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Section section = (Section) session.load(Section.class, sectionId);
			BaseQuestion base = (BaseQuestion) session.load(BaseQuestion.class, baseId);
			Question question = new Question(base, section, mark);
			session.save(question);
			tx.commit();
			session.refresh(section);
			return question;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	public static void setDeleted(long questionId, boolean deleted) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Question question = (Question) session.load(Question.class,
					questionId);
			tx = session.beginTransaction();
			question.setDeleted(deleted);
			tx.commit();
			session.refresh(question.getSection());
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}
	
	public void setSection(long questionId, long newSectionId) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			Question question = (Question) session.load(Question.class, questionId);
			Section oldSection = question.getSection();
			Section newSection = (Section) session.load(Section.class, newSectionId);
			tx = session.beginTransaction();
			question.setSection(newSection);
			tx.commit();
			session.refresh(oldSection);
			session.refresh(newSection);
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static Question create(long sectionId, long baseId)
			{
		return create(sectionId, baseId, 1);
	}

}
