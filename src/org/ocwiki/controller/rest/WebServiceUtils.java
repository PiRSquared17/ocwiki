package org.ocwiki.controller.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ocwiki.controller.rest.util.ErrorResult;
import org.ocwiki.data.CategorizableArticle;
import org.ocwiki.data.Resource;
import org.ocwiki.data.Topic;
import org.ocwiki.db.dao.TopicDAO;

public final class WebServiceUtils {

	public static void assertValid(boolean valid, String errorCode) {
		if (!valid) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND)
					.entity(new ErrorResult(errorCode)).build());
		}
	}

}
