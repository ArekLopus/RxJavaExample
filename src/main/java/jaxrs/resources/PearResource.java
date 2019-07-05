package jaxrs.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.Container;
import utils.ResourceUtils;

@Path("/pear")
@ApplicationScoped
public class PearResource {
	
	private int value = 1000;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response testGet() {
		
		int nextInt = ResourceUtils.checkDirectionGrowing();
		
		Container<Integer> container = new Container<>(value, nextInt);
		
		value += nextInt;
		
		return Response.ok(container).build();
	}
	
}