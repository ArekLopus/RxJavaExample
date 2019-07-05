package grafana;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class GrafanaSetup1FindDashboard {

	public GrafanaSetup1FindDashboard() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient();
		client.register(feature1);
		
		WebTarget getId = client.target("http://localhost:3000/api/dashboards/db/rxjava");
		Response resp = getId.request().get();
		System.out.println("Response: " + resp);
		
		JsonObject entity = resp.readEntity(JsonObject.class);
		
		JsonPointer pointer = Json.createPointer("/dashboard/id");
		JsonValue id = pointer.getValue(entity);
		System.out.println("Id: " + id);
		
		
		client.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) {
		new GrafanaSetup1FindDashboard();

	}

}
