package org.ocwiki.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

public abstract class AbstractListAction<T> extends AbstractAction {

	private long count;
	private int start;
	private int size;
	private List<T> items;
	
	@Override
	public void perform() throws IOException, ServletException {
		super.perform();
		start = getParams().getInt("s", 0);
		if (start < 0) {
			start = 0;
		}
		size = getParams().getInt("z", getDefaultSize());
		if (size <= 0) {
			size = getDefaultSize();
		}
		if (size > getMaxSize()) {
			size = getMaxSize();
		}
		count = getCountImpl();
		items = getItemsImpl();
	}

	protected int getDefaultSize() {
		return 10;
	}
	
	protected int getMaxSize() {
		return 100;
	}
	
	protected abstract long getCountImpl();

	protected abstract List<T> getItemsImpl(); 
	
	public int getStart() {
		return start;
	}
	
	public int getSize() {
		return size;
	}
	
	public long getCount() {
		return count;
	}
	
	public List<T> getItems() {
		return items;
	}
	
}
