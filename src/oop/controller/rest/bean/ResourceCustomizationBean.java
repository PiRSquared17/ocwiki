package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.ResourceLike;
import oop.data.ResourceTodo;

@XmlRootElement
public class ResourceCustomizationBean {

	private ResourceReferenceBean resource;
	private UserReferenceBean user;
	private int level = -1;
	private ResourceLike like = ResourceLike.NORMAL;
	private ResourceTodo todo = ResourceTodo.NORMAL;
	private boolean done;

	public ResourceCustomizationBean() {
	}

	public ResourceReferenceBean getResource() {
		return resource;
	}

	public void setResource(ResourceReferenceBean resource) {
		this.resource = resource;
	}

	public UserReferenceBean getUser() {
		return user;
	}

	public void setUser(UserReferenceBean user) {
		this.user = user;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ResourceLike getLike() {
		return like;
	}

	public void setLike(ResourceLike like) {
		this.like = like;
	}

	public ResourceTodo getTodo() {
		return todo;
	}

	public void setTodo(ResourceTodo todo) {
		this.todo = todo;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
}
