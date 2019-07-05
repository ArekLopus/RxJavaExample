package abc;

import static org.junit.Assert.*;

import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.*;
import org.junit.Before;
import org.junit.Test;

//To test locally -> mvn failsafe:integration-test
public class JaxRSTest {

	private Client client;
	private WebTarget target;


	@Before
	public void init() {
		this.client = ClientBuilder.newClient();
		this.target = this.client.target("http://localhost:8080/health");
	}
	
	
	@Test
	public void testValue() {
		JsonValue jsonValue = target.request().get(JsonValue.class);
		System.out.println(jsonValue);
	}

	@Test
	public void testResponse() {
		Response response = target.request().get();
		assertThat(response.getStatus(), is(200));
	}
}
