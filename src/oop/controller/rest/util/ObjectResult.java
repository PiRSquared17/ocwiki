/**
 * 
 */
package oop.controller.rest.util;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ObjectResult<T> {
	
	public T object;
	
	ObjectResult() {
	}

	public ObjectResult(T object) {
		super();
		this.object = object;
	}
	
}