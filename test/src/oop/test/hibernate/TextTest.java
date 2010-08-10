package oop.test.hibernate;

import oop.data.Text;
import oop.db.dao.TextDAO;

import org.junit.Assert;
import org.junit.Test;

public class TextTest extends HibernateTest {

	public TextTest() {
		super("text.xml");
	}
	
	@Test
	public void fetch() {
		Text text = TextDAO.fetch(2);
		Assert.assertEquals("Việt Nam vô địch!!", text.getText());
	}
	
	@Test
	public void create() {
		Text text = TextDAO.create("thử tạo Text");
		Assert.assertTrue(text.getId() > 0);
	}
	
}
