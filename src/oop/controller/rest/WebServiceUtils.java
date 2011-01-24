package oop.controller.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import oop.controller.rest.util.ErrorResult;
import oop.data.CategorizableArticle;
import oop.data.Resource;
import oop.data.Topic;
import oop.db.dao.TopicDAO;

public final class WebServiceUtils {

	public static void assertValid(boolean valid, String errorCode) {
		if (!valid) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND)
					.entity(new ErrorResult(errorCode)).build());
		}
	}

}
