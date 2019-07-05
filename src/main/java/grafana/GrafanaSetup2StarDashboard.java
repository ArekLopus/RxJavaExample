package grafana;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class GrafanaSetup2StarDashboard {

	public GrafanaSetup2StarDashboard() {
		
		HttpAuthenticationFeature feature1 = HttpAuthenticationFeature.basic("admin", "admin");
		
		Client client = ClientBuilder.newClient();
		client.register(feature1);
		
		WebTarget starring = client.target("http://localhost:3000/api/user/stars/dashboard/3");
		Response resp = starring.request().post(Entity.json("{}"));
		System.out.println("Response: " + resp);
		
		
		client.close();
		
		System.out.println("--- Main Thread Finished ---");
	}

	public static void main(String[] args) {
		new GrafanaSetup2StarDashboard();

	}

}
