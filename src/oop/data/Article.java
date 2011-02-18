package oop.data;

import java.security.InvalidParameterException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import oop.util.Utils;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@XmlRootElement
public abstract class Article implements Entity {

	@DocumentId
	private long id;
	
	@IndexedEmbedded
	private Text content;
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String name;
	
	private String urlName;
	
	@IndexedEmbedded
	private Namespace namespace;

	Article() {
	}

	public Article(Namespace namespace, Text content) {
		this(namespace, "", content);
	}

	public Article(Namespace namespace, String name, Text content) {
		super();
		this.name = name;
		this.namespace = namespace;
		this.content = content;
	}

	@XmlElement
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public Text getContent() {
		return content;
	}

	public void setContent(Text content) {
		this.content = content;
	}
	
	/**
	 * Set the <code>name</code> property of article to specified value.
	 * @param name
	 * @see <a href="http://code.google.com/p/ocwiki/wiki/ArticleName">Article name</a>
	 */
	public void setName(String name) {
		name = standardlizeSpaces(name);
		if (!checkNameImpl(name)) {
			throw new InvalidParameterException("Invalid article name.");
		}
		urlName = Utils.toUrlFriendly(name);
		this.name = name;
	}

	public String getUrlName() {
		return urlName;
	}
	
	public static boolean isValidNameCharacter(char ch) {
		if (ch == '#' || ch == ':' || ch == 127) {
			return false;
		}
		if (ch >= 0 && ch <= 31) {
			return false;
		}
		return true;
	}

	/**
	 * Kiểm tra một chuỗi có phải tên hợp lệ hay không.
	 * 
	 * @param str
	 * @return
	 * @see <a href="http://code.google.com/p/ocwiki/wiki/ArticleName">Article name</a>
	 */
	public static boolean isValidName(String str) {
		str = standardlizeSpaces(str);
		return checkNameImpl(str);
	}

	private static boolean checkNameImpl(String str) {
		if (str == null) {
			return true;
		}
		if (str.length() > 255) {
			return false;
		}
		for (int i = str.length()-1; i >= 0; i--) {
			if (!isValidNameCharacter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Xoá các khoảng trắng ở đầu và cuối chuỗi, thay nhiều khoảng trắng
	 * liên tiếp ở giữa chuỗi bằng một khoảng trắng, thay các kí tự trắng
	 * (tab, lind feed, form feed,...) bằng dấu cách thông thường. 
	 * @param str
	 * @return Xâu đã được chuẩn hoá.
	 */
	public static String standardlizeSpaces(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(str.length());
		int i = 0;
		while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
			i++;
		}
		for (; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isWhitespace(ch)) {
				sb.append(' ');
				while (i < str.length() - 1
						&& Character.isWhitespace(str.charAt(i + 1))) {
					i++;
				}
			} else {
				sb.append(ch);
			}
		}
		if (sb.charAt(sb.length() - 1) == ' ') {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * Trả về tên cơ sở của bài viết
	 * @see <a href="http://code.google.com/p/ocwiki/wiki/ArticleName">Article name</a>
	 * @return Tên cơ sở của bài viết
	 */
	public String getName() {
		return name;
	}

	protected <T> T copyTo(T obj) {
		Article article = (Article) obj;
		article.setName(getName());
		article.setNamespace(getNamespace());
		article.setContent(getContent());
		return obj;
	}

	public abstract Article copy();

	public void setNamespace(Namespace namespace) {
		this.namespace = namespace;
	}

	@XmlElement
	public Namespace getNamespace() {
		return namespace;
	}

	public void setContentString(String contentStr) {
		content = (contentStr == null ? null : new Text(contentStr));
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass().isInstance(obj)) {
			return id == ((Article)obj).id;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Utils.hashCode(id);
	}
	
}
