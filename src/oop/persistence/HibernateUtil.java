package oop.persistence;

import oop.conf.Config;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static ThreadLocal<Session> sessionLocal = new ThreadLocal<Session>();

	public static void init(final Config config) {
		// if (sessionFactory != null) {
		// throw new IllegalStateException(
		// "Session factory is already initialized.");
		// }
		if (sessionFactory != null) {
			sessionFactory.close();
		}

		Configuration hconf = new Configuration();

		// modify table prefixes
		hconf.setNamingStrategy(new PrefixNamingStrategy(config));

		// init database connection
		hconf.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		hconf.setProperty("hibernate.connection.driver_class",
				"com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + config.getDatabaseHost() + ":"
				+ config.getDatabasePort() + "/" + config.getDatabaseName()
				+ "?useUnicode=true&characterEncoding=UTF-8";
		hconf.setProperty("hibernate.connection.url", url);
		hconf
				.setProperty("hibernate.connection.username", config
						.getUserName());
		hconf
				.setProperty("hibernate.connection.password", config
						.getPassword());

		// add classes
		hconf.addClass(oop.data.User.class);
		hconf.addClass(oop.data.FacebookAccount.class);
		hconf.addClass(oop.data.Topic.class);
		hconf.addClass(oop.data.TopicSet.class);
		hconf.addClass(oop.data.Namespace.class);
		hconf.addClass(oop.data.Revision.class);
		hconf.addClass(oop.data.Resource.class);
		hconf.addClass(oop.data.ResourceReport.class);
		hconf.addClass(oop.data.ResourceCustomization.class);
		hconf.addClass(oop.data.CategorizableArticle.class);
		hconf.addClass(oop.data.TextArticle.class);
		hconf.addClass(oop.data.BaseArticle.class);
		hconf.addClass(oop.data.Article.class);
		hconf.addClass(oop.data.Text.class);
		hconf.addClass(oop.data.File.class);
		hconf.addClass(oop.data.BaseQuestion.class);
		hconf.addClass(oop.data.AnswerAttempt.class);
		hconf.addClass(oop.data.Answer.class);
		hconf.addClass(oop.data.Question.class);
		hconf.addClass(oop.data.Section.class);
		hconf.addClass(oop.data.Test.class);
		hconf.addClass(oop.data.SectionStructure.class);
		hconf.addClass(oop.data.TestStructure.class);
		hconf.addClass(oop.data.Constraint.class);
		hconf.addClass(oop.data.TopicConstraint.class);
		hconf.addClass(oop.data.LevelConstraint.class);
		hconf.addClass(oop.data.History.class);
		hconf.addClass(oop.data.Comment.class);
		hconf.addClass(oop.data.CommentReport.class);
		hconf.addClass(oop.data.CommentCustomization.class);
		hconf.addClass(oop.data.log.Log.class);
		hconf.addClass(oop.data.log.ResourceLog.class);
		hconf.addClass(oop.data.log.CommentLog.class);
		hconf.addClass(oop.data.log.RevisionLog.class);
		hconf.addClass(oop.data.log.NewMemberLog.class);

		sessionFactory = hconf.buildSessionFactory();
	}

	public static long count(String hql) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			Long count = (Long) query.uniqueResult();
			tx.commit();
			return count;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
				session.close();
			}
			throw ex;
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		if (sessionLocal.get() == null || !sessionLocal.get().isOpen()) {
			sessionLocal.set(getSessionFactory().openSession());
		}
		return sessionLocal.get();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T load(Class<T> clazz, long id) {
		if (id <= 0) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		Session session = HibernateUtil.getSession();
		return (T) session.load(clazz, id);
	}

	public static void closeSession() {
		if (sessionLocal.get() != null) {
			Session session = sessionLocal.get();
			if (session.isOpen()) {
				session.flush();
				Transaction tx = session.getTransaction();
				if (tx.isActive()) {
					try {
						tx.commit();
					} catch (HibernateException ex) {
						tx.rollback();
					}
				}
				session.close();
			}
			sessionLocal.set(null);
		}
	}

}
