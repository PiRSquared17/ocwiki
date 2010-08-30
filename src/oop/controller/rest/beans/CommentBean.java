/**
 * 
 */
package oop.controller.rest.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import oop.data.CommentStatus;
import oop.data.HasVersion;

@XmlRootElement
public class CommentBean implements HasVersion {

	@XmlElement
	public long userId;
	@XmlElement
	public long resourceId;
	@XmlElement
	public long revisionId;
	@XmlElement
	public int version;
	@XmlElement
	public String message;
	@XmlElement
	public CommentStatus status;

	public CommentBean() {
	}
	
	public CommentBean(long userId, long resourceId, long revisionId,
			int version, String message, CommentStatus status) {
		super();
		this.userId = userId;
		this.resourceId = resourceId;
		this.revisionId = revisionId;
		this.version = version;
		this.message = message;
		this.status = status;
	}

	@Override
	public int getVersion() {
		return version;
	}
	
}