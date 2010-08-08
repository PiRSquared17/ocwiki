package oop.db.dao;

import oop.data.SectionStructure;
import oop.data.TopicConstraint;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TopicConstraintDAO {

	public static TopicConstraint create(long sectionStructureId,
			int count) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			SectionStructure sectionStructure = (SectionStructure) session
					.load(SectionStructure.class, sectionStructureId);
			TopicConstraint constraint = new TopicConstraint(sectionStructure,
					count);
			tx = session.beginTransaction();
			session.save(constraint);
			tx.commit();
			session.refresh(sectionStructure);
			return constraint;
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

}
