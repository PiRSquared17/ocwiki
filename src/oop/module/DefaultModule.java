package oop.module;

import oop.conf.ModuleDescriptor;
import oop.controller.Parameter;
import oop.data.Article;
import oop.data.Resource;

public class DefaultModule implements Module {

	private String title;
	private String page;
	private int position;
	private Resource<? extends Article> resource;
	private ModuleDescriptor descriptor;
	
	@Override
	public void init() throws Exception {
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String getPage() {
		return page;
	}
	
	@Override
	public void setPage(String page) {
		this.page = page;
	}

	public void setOrder(int position) {
		this.position = position;
	}

	public int getOrder() {
		return position;
	}
	
	@Override
	public Resource<? extends Article> getResource() {
		return resource;
	}
	
	@Override
	public <T extends Article> void setResource(Resource<T> resource) {
		this.resource = resource;
	}

	@Override
	public Article getArticle() {
		return resource == null ? null : resource.getArticle();
	}
	
	@Override
	public ModuleDescriptor getDescriptor() {
		return descriptor;
	}
	
	@Override
	public void setDescriptor(ModuleDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Trả về giá trị của tham số có tên <code>name</code>. Nếu tham số không
	 * tồn tại, trả về giá trị null.
	 * 
	 * @param name
	 * @return
	 */
	public String getParam(String name) {
		for (Parameter param : getDescriptor().getParameters()) {
			if (name.equals(param.getName())) {
				return param.getValue();
			}
		}
		return null;
	}
	
}
