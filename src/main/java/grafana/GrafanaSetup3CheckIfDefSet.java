package grafana;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class GrafanaSetup3CheckIfDefSet {

	public GrafanaSetup3CheckIfDefSet() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient();
		client.register(feature1);
		
		WebTarget checkIfsetDef = client.target("http://localhost:3000/api/user/preferences");
		
		Response resp = checkIfsetDef
			.request()
			.get();
		
		JsonObject entity = resp.readEntity(JsonObject.class);
		
		System.out.println(resp);
		System.out.println(entity.getInt("homeDashboardId"));		// If 0 -> no default dashboard set
		
		
		client.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) {
		new GrafanaSetup3CheckIfDefSet();

	}

}
