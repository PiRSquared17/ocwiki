package oop.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResourceReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Resource<? extends Article> resource;
	private User user;
	private int level;
	private ResourceLike like;
	private ResourceTodo todo;
	private int likeCount;
	private double averageLevel;

	public ResourceReport() {
	}

	public ResourceReport(Resource<? extends Article> resource, User user,
			int level, ResourceLike like, ResourceTodo todo, int likeCount,
			double averageLevel) {
		super();
		this.resource = resource;
		this.user = user;
		this.level = level;
		this.like = like;
		this.todo = todo;
		this.likeCount = likeCount;
		this.averageLevel = averageLevel;
	}

	public ResourceReport(Resource<? extends Article> resource, int likeCount,
			double averageLevel) {
		super();
		this.resource = resource;
		this.likeCount = likeCount;
		this.averageLevel = averageLevel;
		this.user = null;
		this.level = 0;
		this.like = ResourceLike.NORMAL;
		this.todo = ResourceTodo.NORMAL;
	}

	public Resource<? extends Article> getResource() {
		return resource;
	}

	public void setResource(Resource<? extends Article> resource) {
		this.resource = resource;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public double getAverageLevel() {
		return averageLevel;
	}

	public void setAverageLevel(double averageLevel) {
		this.averageLevel = averageLevel;
	}

}
