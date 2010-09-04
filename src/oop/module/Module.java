package oop.module;

public interface Module {
	
	void init() throws Exception;
	
	String getTitle();
	
	void setTitle(String title);
	
	String getPage();
	
	void setPage(String page);

	public void setOrder(int position);

	public int getOrder();
	
}
