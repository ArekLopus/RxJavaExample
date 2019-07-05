package rx;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Container;

public class Testing {
	
	private Client client;
	
	
	@Test
    public void test() {
		
		WebTarget pearTtarget = client.target("http://localhost:8080/RxJavaExample/res/container");
		Response response = pearTtarget.request().get();
		Container<Integer> entity = response.readEntity(new GenericType<Container<Integer>>(){});
		
//		System.out.println(response);
//		System.out.println(entity);
		
		assertThat(response.getStatus(), is(200));
		assertNotNull(entity);
		assertNotNull(entity.getValue());
		assertNotNull(entity.getChange());
		
    }
	
	
	@Before
	public void init() {
		client = ClientBuilder.newClient();
	}
	
	@After
	public void finish() {
		client.close();
	}
}