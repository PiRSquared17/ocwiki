package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.CommentReport;
import org.ocwiki.data.CommentStatus;
import org.ocwiki.db.dao.CommentReportDAO;

import org.junit.Assert;
import org.junit.Test;

public class CommentReportTest extends HibernateTest {

	@Test
	public void testFetch() {
		List<CommentReport> entries = CommentReportDAO
				.fetchByResourceAndUser(88, 1, 0, 10);
		Assert.assertEquals(2, entries.size());
		
		CommentReport report1 = entries.get(0);
		Assert.assertEquals(CommentStatus.HIDDEN, report1.getStatus());
		Assert.assertEquals(1, report1.getComment().getId());
		Assert.assertEquals("comment 1234", report1.getComment()
				.getMessage());

	}

	@Test
	public void testFetchNoUser() {
		List<CommentReport> entries = CommentReportDAO
				.fetchByResourceAndUser(88, 0, 0, 10);
		Assert.assertEquals(2, entries.size());
		
		CommentReport report1 = entries.get(0);
		Assert.assertNull(report1.getUser());
		Assert.assertEquals(CommentStatus.NORMAL, report1.getStatus());
		Assert.assertEquals("comment 1234", report1.getComment()
				.getMessage());

	}

}
