/**
 * 
 */
package oop.controller.rest.util;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListResult<T> {
	
	public List<T> result;
	public String next;
	
	ListResult() {
	}
	
	public ListResult(List<T> result, String next) {
		super();
		this.result = result;
		this.next = next;
	}
	
}