package oop.test;

import org.junit.Assert;
import org.junit.Test;
import static oop.data.Article.standardlizeSpaces;
import static oop.data.Article.isValidName;

public class ArticleNameTest {

	@Test
	public void standalize() {
		Assert.assertEquals("abc", standardlizeSpaces("  abc   "));
		Assert.assertEquals("a b c d e", standardlizeSpaces("a b  c d  e"));
		Assert.assertEquals("a b c d e", standardlizeSpaces("a\tb\fc\nd\re"));
	}
	
	@Test
	public void invalid() {
		Assert.assertTrue(isValidName("a\tb\fc\nd\re"));
		Assert.assertFalse(isValidName("a#e"));
		Assert.assertFalse(isValidName("a:e"));
		Assert.assertTrue(isValidName("a&nbsp;e"));
		Assert.assertTrue(isValidName("a<e"));
	}
	
}
