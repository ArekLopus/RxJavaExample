package grafana;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class GrafanaSetup4SetDbAsDef {

	public GrafanaSetup4SetDbAsDef() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient();
		client.register(feature1);
		
		WebTarget setDef = client.target("http://localhost:3000/api/user/preferences");
		Response resp = setDef
				.request()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.put(Entity.json(Json.createObjectBuilder().add("homeDashboardId", 0).build()));	// 0 -> no default dashboard
		System.out.println("Response: " + resp);
		
		
		client.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) {
		new GrafanaSetup4SetDbAsDef();

	}

}
