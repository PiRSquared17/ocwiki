package oop.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import oop.controller.rest.util.ErrorResult;
import oop.controller.rest.util.InvalidParamResult;
import oop.data.Article;
import oop.data.HasVersion;
import oop.data.Resource;
import oop.data.Revision;
import oop.data.User;
import oop.db.dao.ResourceDAO;
import oop.util.SessionUtils;

import com.oreilly.servlet.ParameterList;
import com.oreilly.servlet.ParameterNotFoundException;
import com.oreilly.servlet.ParameterParser;

@Consumes( { MediaType.APPLICATION_JSON })
@Produces( { MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
public abstract class AbstractResource {

	public static final int MAX_PAGE_SIZE = 50;
	
	@Context
	private HttpServletRequest request;
	private ParameterList params;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	protected <T extends Article> Revision<T> saveNewRevision(
			Resource<T> resource, T article, String summary, boolean minor) {
		User user = SessionUtils.getUser(getSession());
		return ResourceDAO.update(resource, article, user, summary, minor);
	}

	protected int getBaseVersion() {
		try {
			return getParams().getInt("basever");
		} catch (NumberFormatException e) {
			throw invalidParam("basever", "number format");
		} catch (ParameterNotFoundException e) {
			throw invalidParam(e.getName(), "param not found");
		}
	}
	
	protected User getUser() {
		return SessionUtils.getUser(getSession());
		
	}

	protected ParameterList getParams() {
		if (params == null) {
			params = new ParameterParser(request);
		}
		return params;
	}
	
	protected void assertParamValid(boolean valid, String name, String errorCode) {
		if (!valid) {
			throw invalidParam(name, errorCode);
		}
	}

	protected void assertParamFound(String name) {
		assertParamValid(getParams().hasParameter(name), name,
				"param not found");
	}

	protected void assertResourceFound(Object obj) {
		if (obj == null) {
			throw resourceNotFound();
		}
	}

	protected WebApplicationException resourceNotFound() {
		return new WebApplicationException(Response.status(Status.NOT_FOUND)
				.entity(new ErrorResult("not found")).build());
	}

	protected WebApplicationException invalidParam(String name, String errorCode) {
		return new WebApplicationException(Response.status(Status.BAD_REQUEST)
				.entity(
						new InvalidParamResult(errorCode, name, getParams()
								.get(name))).build());
	}

	protected <T extends Article> Resource<T> getResourceSafe(long id, Class<T> type) {
		Resource<T> resource = ResourceDAO.fetchById(id);
		assertResourceFound(resource);
		T article = resource.getArticle();
		assertResourceFound(article);
		if (!(type.isInstance(article))) {
			throw resourceNotFound();
		}
		return resource; 			
	}

	protected void assertVersion(HasVersion user, HasVersion data) {
		if (user.getVersion() != data.getVersion()) {
			throw new WebApplicationException(Response.status(
					Status.BAD_REQUEST).entity(new ErrorResult("old version"))
					.build());
		}
	}

	protected void assertBaseVersion(HasVersion entity) {
		if (entity.getVersion() != getBaseVersion()) {
			throw invalidParam("basever", "old version");	
		}
	}

}
