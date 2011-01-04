package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ObjectUtils;

@XmlRootElement
public class ResourceCustomization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int LEVEL_HARD = 1;
	public static final int LEVEL_EASY = 0;
	public static final int LEVEL_NORMAL = -1;

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

	public ResourceCustomization(Resource<? extends Article> resource, User user) {
		this(resource, user, -1, ResourceLike.NORMAL, ResourceTodo.NORMAL);
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ResourceCustomization) {
			ResourceCustomization rc = (ResourceCustomization) obj;
			return ObjectUtils.equals(resource, rc.resource)
					&& ObjectUtils.equals(user, rc.user);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return ObjectUtils.hashCode(resource) ^ ObjectUtils.hashCode(user);
	}
	
}
