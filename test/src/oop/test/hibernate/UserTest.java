package oop.test.hibernate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import oop.data.User;
import oop.db.dao.UserDAO;
import oop.persistence.HibernateUtil;

import org.hibernate.StaleObjectStateException;
import org.junit.Assert;
import org.junit.Test;

public class UserTest extends HibernateTest {

	public UserTest() {
		super("user.xml");
	}

	@Test
	public void testFetch() {
		List<User> users = UserDAO.fetch(0, 3);
		Assert.assertEquals(2, users.size());
	}

	@Test
	public void testFetchById() {
		User admin = UserDAO.fetchById(1);
		Assert.assertEquals("admin", admin.getName());
		Assert.assertEquals("admin", admin.getGroup());
		Assert.assertEquals(false, admin.isBlocked());
		Assert.assertEquals(new GregorianCalendar(2010, 0, 1, 0, 0, 1)
				.getTime(), admin.getRegisterDate());
		Assert.assertEquals("advanded", admin.getPreferences().getTemplate());

		User teacher = UserDAO.fetchById(2);
		Assert.assertEquals("teacher", teacher.getName());
		Assert.assertEquals("teacher", teacher.getGroup());
		Assert.assertEquals(false, teacher.isBlocked());
		Assert.assertEquals(new GregorianCalendar(2010, 07, 25, 0, 47, 13)
				.getTime(), teacher.getRegisterDate());
		Assert.assertEquals("default", teacher.getPreferences().getTemplate());
	}

	@Test
	public void testFetchByEmail() {
		User admin = UserDAO.fetchByEmail("admin@ocwiki.org");
		Assert.assertEquals("admin", admin.getName());

		User teacher = UserDAO.fetchByEmail("teacher@ocwiki.org");
		Assert.assertEquals("teacher", teacher.getName());
	}

	@Test
	public void testFetchByUsername() {
		User admin = UserDAO.fetchByUsername("admin");
		Assert.assertEquals(1L, admin.getId());

		User teacher = UserDAO.fetchByUsername("teacher");
		Assert.assertEquals(2L, teacher.getId());
	}

	@Test
	public void testCount() {
		Assert.assertEquals(2, UserDAO.count());
	}

	@Test
	public void testUpdate() {
		User admin = UserDAO.fetchById(1);
		admin.setBlocked(true);
		GregorianCalendar cal = new GregorianCalendar();
		admin.getPreferences().setTemplate("newtemp");
		cal.add(Calendar.DATE, 1);
		admin.setBlockExpiredDate(cal.getTime());
		Assert.assertEquals(true, admin.isBlocked());
		UserDAO.persist(admin);

		Assert.assertEquals(true, admin.isBlocked());
		Assert.assertEquals(cal.getTime(), admin.getBlockExpiredDate());
		Assert.assertEquals("newtemp", admin.getPreferences().getTemplate());
	}

	/**
	 * Kiểm tra tính năng optimistic lock. Thread chạy không ổn định nên kết
	 * quả thường bị sai lệch.
	 * @throws InterruptedException
	 */
//	@Test
	public void testVersionCheck() throws InterruptedException {
		final BlockingQueue<Boolean> queue1 = new ArrayBlockingQueue<Boolean>(3);
		final BlockingQueue<Boolean> queue2 = new ArrayBlockingQueue<Boolean>(3);
		final Exception[] exceptions = { null, null };
		
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					User admin = UserDAO.fetchById(1); // same base version
					System.out.println("thread1 read");
					queue1.take(); // wait for thread2 to read
					admin.setAvatar("nothing.png");
					UserDAO.persist(admin);
					HibernateUtil.closeSession();
					System.out.println("thread1 read");
					queue2.put(true); // notify thread2
				} catch (StaleObjectStateException e) {
					exceptions[1] = e;
				} catch (InterruptedException e) {
					exceptions[1] = e;
					e.printStackTrace();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					User admin = UserDAO.fetchById(1); // same base version
					queue1.put(true); // notify thread1 user has bean read
					queue2.take(); // wait for thread1
					admin.setAvatar("empty.png");
					UserDAO.persist(admin);
					HibernateUtil.closeSession();
				} catch (StaleObjectStateException e) {
					exceptions[0] = e;
				} catch (InterruptedException e) {
					exceptions[0] = e;
					e.printStackTrace();
				}
			}
		});

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();

		System.out.println(exceptions[0]);
		Assert.assertNotNull(exceptions[0]);
		Assert.assertEquals(exceptions[0].getClass(),
				StaleObjectStateException.class);
		Assert.assertNull(exceptions[1]);
	}

}
