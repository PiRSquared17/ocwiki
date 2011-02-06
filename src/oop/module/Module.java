package oop.module;

import oop.conf.ModuleDescriptor;
import oop.data.Article;
import oop.data.Resource;

public interface Module {

	void init() throws Exception;

	String getTitle();

	void setTitle(String title);

	String getPage();

	void setPage(String page);

	void setOrder(int position);

	int getOrder();

	/**
	 * Trả về đối tượng resource trong trang hiện tại (nếu có). 
	 * @return
	 */
	Resource<? extends Article> getResource();
	
	/**
	 * ActionController dùng hàm này để truyền đối tượng tài
	 * nguyên đang được sử dụng trong trang hiện tại (nếu có). 
	 * @param <T>
	 * @param resource
	 */
	<T extends Article> void setResource(Resource<T> resource);
	
	Article getArticle();
	
	ModuleDescriptor getDescriptor();

	void setDescriptor(ModuleDescriptor descriptor);
	
}
