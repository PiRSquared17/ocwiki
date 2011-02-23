package org.ocwiki.data.log;

import java.util.Date;

import org.ocwiki.data.Article;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Revision;
import org.ocwiki.data.User;

public class NewRevisionLog extends ResourceLog {

	private Revision<? extends Article> newRevision;
	private Revision<? extends Article> oldRevision;

	public NewRevisionLog(User user, Date timestamp,
			Resource<? extends Article> resource,
			Revision<? extends Article> newRevision,
			Revision<? extends Article> oldRevision) {
		super(user, timestamp, resource);
		this.newRevision = newRevision;
		this.oldRevision = oldRevision;
	}

	NewRevisionLog() {
	}

	public Revision<? extends Article> getOldRevision() {
		return oldRevision;
	}

	public Revision<? extends Article> getNewRevision() {
		return newRevision;
	}

}
