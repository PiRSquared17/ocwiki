package oop.data;

public class Namespace {

	public static final Namespace MAIN = new Namespace(0, "", "Chính");
	public static final Namespace QUESTION = new Namespace(-1, "cau-hoi", "Câu hỏi");
	public static final Namespace TEST = new Namespace(-2, "de-thi", "Đề thi");
	public static final Namespace TEST_STRUCTURE = new Namespace(-3, "cau-truc", "Cấu trúc đề");
	public static final Namespace SOLUTION = new Namespace(-4, "bai-giai", "Bài giải");
	public static final Namespace TOPIC = new Namespace(-5, "chu-de", "Chủ đề");

	private long id;
	private String name;
	private String displayName;

	Namespace() {
	}
	
	public Namespace(long id, String name, String displayName) {
		super();
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
