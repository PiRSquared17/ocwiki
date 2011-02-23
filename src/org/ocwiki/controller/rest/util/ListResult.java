/**
 * 
 */
package org.ocwiki.controller.rest.util;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import org.ocwiki.conf.Config;

@XmlRootElement
public class ListResult<T> {
	
	public Collection<T> result;
	public String next;
	public long totalCount;
	
	ListResult() {
	}

	public ListResult(Collection<T> result) {
		this(result, null);
	}
	
	public ListResult(Collection<T> result, String next) {
		super();
		this.result = result;
		this.next = (next == null ? null : Config.get().getRestPath() + next);
	}
	
	public ListResult(Collection<T> result, String next, long totalCount) {
		super();
		this.result = result;
		this.next = (next == null ? null : Config.get().getRestPath() + next);
		this.totalCount = totalCount;
	}
	
}