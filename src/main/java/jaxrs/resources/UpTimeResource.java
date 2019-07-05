package jaxrs.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.Container;

@Path("/uptime")
@ApplicationScoped
public class UpTimeResource {
	
	private long start = System.currentTimeMillis();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response testGet() {
		
		long upTimeInSeconds = ((System.currentTimeMillis() - start) / 1000) + 1;
		
		Container<Long> container = new Container<>(upTimeInSeconds, start);
		
		return Response.ok(container).build();
	}
	
	
}