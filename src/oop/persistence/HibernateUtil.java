package oop.persistence;

import oop.conf.Config;
import oop.controller.OcwikiApp;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Config config;
	private static SessionFactory sessionFactory = null;
	private static ThreadLocal<Session> sessionLocal = new ThreadLocal<Session>();

	public static void setConfig(final Config config) {
		HibernateUtil.config = config;
		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
		if (!config.isLazyStartup()) {
			init();
		} else {
			// get it initialized in a different thread
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					HibernateUtil.getSessionFactory();
				}
			}).start();
		}
	}

	private static void init() {
		Configuration hconf = new Configuration();

		hconf.configure("hibernate.cfg.xml");
		
		// modify table prefixes
		//hconf.setNamingStrategy(new PrefixNamingStrategy(config));

		// init database connection
		hconf.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLDialect");
		hconf.setProperty("hibernate.connection.driver_class",
				"com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + config.getDatabaseHost() + ":"
				+ config.getDatabasePort() + "/" + config.getDatabaseName()
				+ "?useUnicode=true&characterEncoding=UTF-8";
		hconf.setProperty("hibernate.connection.url", url);
		hconf.setProperty("hibernate.connection.username", config
						.getUserName());
		hconf.setProperty("hibernate.connection.password", config
						.getPassword());

		// config Hibernate search
		hconf.setProperty("hibernate.search.default.directory_provider",
				"org.hibernate.search.store.FSDirectoryProvider");
		String indexBasePath = config.getLuceneIndexDirectory();
		if (OcwikiApp.get() != null) {
			indexBasePath = OcwikiApp.get().getServletContext()
					.getRealPath(indexBasePath);
		}
		hconf.setProperty("hibernate.search.default.indexBase", indexBasePath);

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

	/**
	 * Use double checking to initialize session factory.
	 * I know that double checking is not safe but it rarely fails and we use
	 * lazy startup only in development so it's OK.
	 * @author cumeo89
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null && config.isLazyStartup()) {

			synchronized (config) {
				if (sessionFactory == null) {
					init();
				}
			}
		}
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
			return null;
		}
		return (T) getSession().load(clazz, id);
	}

	public static void closeSession() {
		if (sessionLocal.get() != null) {
			Session session = sessionLocal.get();
			if (session != null && session.isOpen()) {
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
