package jaxrs;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

//http://localhost:8080/health
@Health
@ApplicationScoped
public class LivenessTest implements HealthCheck {
	
	private AtomicInteger counter = new AtomicInteger(0);
	
	@Override
	public HealthCheckResponse call() {
		
		HealthCheckResponse hcr = HealthCheckResponse
			.named("Microprofile Health Check")
			.up()
			.withData("Health Check Counter", counter.incrementAndGet())
			.build();
		
		return hcr;
	}

}