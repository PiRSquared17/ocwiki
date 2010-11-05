package oop.data.log;

import java.util.Date;

import oop.data.Article;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;

public class RevisionLog extends ResourceLog {

	private Revision<? extends Article> newRevision;
	private Revision<? extends Article> oldRevision;

	public RevisionLog(User user, Date timestamp,
			Resource<? extends Article> resource,
			Revision<? extends Article> newRevision,
			Revision<? extends Article> oldRevision) {
		super(user, timestamp, resource);
		this.newRevision = newRevision;
		this.oldRevision = oldRevision;
	}

	RevisionLog() {
	}

	public Revision<? extends Article> getOldRevision() {
		return oldRevision;
	}

	public Revision<? extends Article> getNewRevision() {
		return newRevision;
	}

}
