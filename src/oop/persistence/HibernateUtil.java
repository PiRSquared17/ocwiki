package oop.persistence;

import oop.conf.Config;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.DefaultNamingStrategy;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static ThreadLocal<Session> sessionLocal = new ThreadLocal<Session>();

	@SuppressWarnings("serial")
	public static void init(final Config config) {
		if (sessionFactory != null) {
			throw new IllegalStateException(
					"Session factory is already initialized.");
		}

		Configuration hconf = new Configuration();

		// init database connection
		hconf.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		hconf.setProperty("hibernate.connection.driver_class",
				"com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + config.getDatabaseHost() + ":"
				+ config.getDatabasePort() + "/" + config.getDatabaseName()
				+ "?useUnicode=true&characterEncoding=UTF-8";
		hconf.setProperty("hibernate.connection.url", url);
		hconf.setProperty("hibernate.connection.username", config.getUserName());
		hconf.setProperty("hibernate.connection.password", config.getPassword());
		hconf.setProperty("hibernate.cache.provider_class", 
				"org.hibernate.cache.NoCacheProvider");

		// modify table prefix
		hconf.setNamingStrategy(new DefaultNamingStrategy() {
			
			@Override
			public String tableName(String tableName) {
				return config.getTablePrefix()
						+ Character.toUpperCase(tableName.charAt(0))
						+ tableName.substring(1);
			}
		});

		// add classes
		hconf.addClass(oop.data.User.class);
		hconf.addClass(oop.data.Topic.class);
		hconf.addClass(oop.change.Change.class);
		hconf.addClass(oop.data.BaseArticle.class);
		hconf.addClass(oop.data.Article.class);
		hconf.addClass(oop.data.Text.class);
		hconf.addClass(oop.data.BaseQuestion.class);
		hconf.addClass(oop.data.Answer.class);
		hconf.addClass(oop.data.Question.class);
		hconf.addClass(oop.data.Section.class);
		hconf.addClass(oop.data.Test.class);
		hconf.addClass(oop.data.SectionStructure.class);
		hconf.addClass(oop.data.TestStructure.class);
		hconf.addClass(oop.data.Constraint.class);
		hconf.addClass(oop.data.TopicConstraint.class);
		hconf.addClass(oop.data.LevelConstraint.class);
		
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
			if (tx != null)
				tx.rollback();
			throw ex;
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		if (sessionLocal.get() == null) {
			sessionLocal.set(getSessionFactory().openSession());
		}
		return sessionLocal.get();
	}

	public static void closeSession() {
		if (sessionLocal.get() != null) {
			Session session = sessionLocal.get();
			if (session.isOpen()) {
				try {
					session.flush();
					Transaction tx = session.getTransaction();
					if (tx.isActive()) {
						tx.commit();
					}
				} finally {
					session.close();
				}
			}
			sessionLocal.set(null);
		}
	}

}
