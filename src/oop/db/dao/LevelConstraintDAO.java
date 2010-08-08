package oop.db.dao;

import oop.data.LevelConstraint;
import oop.data.SectionStructure;
import oop.persistence.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LevelConstraintDAO {

	public static LevelConstraint create(long sectionStructureId, int level,
			int count) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			SectionStructure sectionStructure = (SectionStructure) session
					.load(SectionStructure.class, sectionStructureId);
			LevelConstraint constraint = new LevelConstraint(sectionStructure,
					level, count);
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
