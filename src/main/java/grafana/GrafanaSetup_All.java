package grafana;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class GrafanaSetup_All {

	
	public static void setHomeDashboard() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient();
		client.register(feature1);
		
		int dashboardNr = -1;
		String host = "grafana";
		
		dashboardNr = GrafanaSetup_All.checkHost(client, "grafana");
		
		// Coundn't connect to grafana host, tries localhost
		if(dashboardNr < 0) {
			dashboardNr = GrafanaSetup_All.checkHost(client, "localhost");
			
			// Connection successful, set host as "localhost" for further operations
			if(dashboardNr >= 0)
				host = "localhost";
		}
		
		// Coundn't connect to both, terminate.
		if(dashboardNr < 0) {
			System.out.println("Cant upgdate Home Dashboard.");
			client.close();
			return;
		}
		
		if(dashboardNr > 0) {
			System.out.println("Home Dashboard is already set.");
			client.close();
			return;
		}
		
		
		if (dashboardNr == 0) {			// If 0 == no default dashboard set
			System.out.println("Home Dashboard is not set, setting a new one.");
			GrafanaSetup_All.setDefaultDashboard(client, host);
			
		}
		
		
		client.close();
	}
	
	
	private static int checkHost(Client client, String host) {
		
		try {
			WebTarget checkIfsetDef = client.target("http://" + host + ":3000/api/user/preferences");
			Response resp = checkIfsetDef
					.request()
					.get();
			System.out.println(resp);
			JsonObject entity = resp.readEntity(JsonObject.class);
			return entity.getInt("homeDashboardId", -1);
		
		} catch (Exception e) {
			System.out.println("Exception for host: " + host + ", message: " + e.getMessage());
			return -1;
		}
	}
	
	
	private static void setDefaultDashboard(Client client, String host) {
		WebTarget getId = client.target("http://" + host + ":3000/api/dashboards/db/rxjava");
		Response resp1 = getId.request().get();
		System.out.println("Response: " + resp1);
		
		JsonObject entity2 = resp1.readEntity(JsonObject.class);
		
		JsonPointer pointer = Json.createPointer("/dashboard/id");
		JsonValue id = pointer.getValue(entity2);
		System.out.println("Id = " + id);
		
		
		WebTarget starring = client.target("http://" + host + ":3000/api/user/stars/dashboard/" + id);
		Response resp2 = starring.request().post(Entity.json("{}"));
		System.out.println("Response: " + resp2);
		
		WebTarget setDef = client.target("http://" + host + ":3000/api/user/preferences");
		Response resp3 = setDef
				.request()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				//.put(Entity.json("{\"homeDashboardId\":" + id +"}"));
				.put(Entity.json(Json.createObjectBuilder().add("homeDashboardId", id).build()));
		System.out.println("Response: " + resp3);
		
	}
	
	
	public GrafanaSetup_All() {
		
		GrafanaSetup_All.setHomeDashboard();
		System.out.println("--- Main Thread Finished ---");
		
	}
	
	public static void main(String[] args) {
		new GrafanaSetup_All();
	}

}
