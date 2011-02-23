package org.ocwiki.test.hibernate;

import java.util.List;

import org.ocwiki.data.TopicReport;
import org.ocwiki.db.dao.TopicReportDAO;

import org.junit.Assert;
import org.junit.Test;

public class TopicReportTest extends HibernateTest {

	@Test
	public void fetchById() {
		TopicReport report = TopicReportDAO.fetchById(465);
		Assert.assertEquals(465, report.getResource().getId());
		Assert.assertEquals(6, report.getChildrenCount());
		Assert.assertEquals(245, report.getArticleCount());
	}
	
	@Test
	public void fetchChildren() {
		List<TopicReport> children = TopicReportDAO.fetchChildren(465);
		Assert.assertEquals(6, children.size());
	}
	
}
