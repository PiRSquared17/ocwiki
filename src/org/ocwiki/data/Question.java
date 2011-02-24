package org.ocwiki.data;

public abstract class Question extends BaseArticle {

	protected int level;

	public static String getLevelName(int level) {
		switch (level) {
		case 1:
			return "Rất khó";
		case 2:
			return "Khó";
		case 3:
			return "Trung bình";
		case 4:
			return "Dễ";
		case 5:
			return "Rất dễ";
		}
		throw new IllegalStateException("độ khó không rõ");
	}

	public Question() {
		super();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Question(Namespace namespace, Text content) {
		super(namespace, content);
	}

}