/**
 * 
 */
package oop.controller.rest.util;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListResult<T> {
	
	public List<T> list;
	public String next;
	
	ListResult() {
	}
	
	public ListResult(List<T> list, String next) {
		super();
		this.list = list;
		this.next = next;
	}
	
}