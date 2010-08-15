/**
 * 
 */
package oop.controller.rest.util;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListResult<T> {
	
	public Collection<T> result;
	public String next;
	
	ListResult() {
	}

	public ListResult(Collection<T> result) {
		this(result, null);
	}
	
	public ListResult(Collection<T> result, String next) {
		super();
		this.result = result;
		this.next = next;
	}
	
}