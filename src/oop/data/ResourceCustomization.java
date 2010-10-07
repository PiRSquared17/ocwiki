package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResourceCustomization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Resource<? extends Article> resource;
	private User user;
	private int level = -1;
	private ResourceLike like = ResourceLike.NORMAL;
	private ResourceTodo todo = ResourceTodo.NORMAL;
	private boolean done;
	
	public ResourceCustomization() {
	}

	public ResourceCustomization(Resource<? extends Article> resource,
			User user, int level, ResourceLike like, ResourceTodo todo) {
		super();
		this.resource = resource;
		this.user = user;
		this.level = level;
		this.like = like;
		this.todo = todo;
	}

	public Resource<? extends Article> getResource() {
		return resource;
	}

	public void setResource(Resource<? extends Article> resource) {
		this.resource = resource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}

}
