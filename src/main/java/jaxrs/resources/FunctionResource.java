package jaxrs.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.Container;
import utils.ResourceUtils;

@Path("/function")
@ApplicationScoped
public class FunctionResource {
	
	private double counter = 0;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response testGet() {
		
		double val = ResourceUtils.sinFunc(counter);
		
		Container<Number> container = new Container<>(val, counter);
		
		counter += 0.1;
		
		return Response.ok(container).build();
	}
	
}