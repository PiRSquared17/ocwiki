package org.ocwiki.controller.rest.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ocwiki.controller.rest.util.ErrorResult;
import org.ocwiki.util.BlockedUserException;

@Provider
public class BlockedUserExceptionMapper implements
		ExceptionMapper<BlockedUserException> {

	@Override
	public Response toResponse(BlockedUserException arg0) {
		return Response.status(Status.FORBIDDEN).entity(
				new ErrorResult("account blocked")).build();
	}

}
