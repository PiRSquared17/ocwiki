package org.ocwiki.test.hibernate;

import org.ocwiki.db.dao.ResourceReportDAO;

import org.junit.Test;

public class ResourceReportTest extends HibernateTest {

	@Test
	public void testFetch() {
		ResourceReportDAO.fetchByResourceAndUser(88, 1);
		ResourceReportDAO.fetchByResourceAndUser(88, 0);
	}
	
}
