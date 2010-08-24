package oop.controller.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import oop.controller.rest.util.ListResult;
import oop.controller.rest.util.ObjectResult;
import oop.data.Revision;
import oop.data.Resource;
import oop.data.Text;
import oop.db.dao.ResourceDAO;
import oop.db.dao.RevisionDAO;

@Path("/revisions")
public class RevisionResource extends AbstractResource{
	
	@GET
	@Path("/{revIndex: \\d+}")
	public ObjectResult<Revision> get(@PathParam("revIndex") int revIndex){		
		Revision revision = (Revision) RevisionDAO.fetch(revIndex);
		return new ObjectResult<Revision>(revision);
	}
	
	@GET
	@Path("/resource/{resourceID: \\d+}/page/{pageIndex: \\d+}")
	public ListResult<Revision> listByResource(@PathParam("resourceID")
			int resourceID, @PathParam("pageIndex") int pageIndex){
		return (ListResult<Revision>) RevisionDAO.
		fetchByResource(resourceID, (pageIndex -1)*50, 50);
	}
	
}
