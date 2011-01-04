package oop.controller.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import oop.data.ResourceLike;
import oop.data.ResourceTodo;

@XmlRootElement
public class ResourceReportBean {

	private ResourceReferenceBean resource;
	private UserReferenceBean user;
	private int level;
	private ResourceLike like;
	private ResourceTodo todo;
	private int likeCount;
	private double averageLevel;

	public ResourceReportBean() {
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public double getAverageLevel() {
		return averageLevel;
	}

	public void setAverageLevel(double averageLevel) {
		this.averageLevel = averageLevel;
	}
	
}
