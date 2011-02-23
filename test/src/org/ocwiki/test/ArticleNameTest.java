package org.ocwiki.test;

import org.junit.Assert;
import org.junit.Test;
import static org.ocwiki.data.Article.standardlizeSpaces;
import static org.ocwiki.data.Article.isValidName;

public class ArticleNameTest {

	@Test
	public void standalize() {
		Assert.assertEquals("a\u0007", standardlizeSpaces("a\u0007"));
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
		Assert.assertTrue(isValidName(null));
		Assert.assertTrue(isValidName(replicate('a', 255)));
		Assert.assertFalse(isValidName(replicate('a', 256)));
	}

	private String replicate(char ch, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(ch);
		}
		return sb.toString();
	}
	
}
