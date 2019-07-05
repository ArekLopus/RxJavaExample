package action;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import grafana.GrafanaSetup_All;
import rx.RxJavaService;
import utils.Container;

@Singleton
@Startup
public class Grabber {
	
	@Inject
	RxJavaService rxService;
	
	@Resource
	TimerService ts;
	
	@Resource
	ManagedScheduledExecutorService mses;
	
	@PostConstruct
	private void init() {
		
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		
		System.out.println("--- Grabbing data is delayed for 10 seconds to allow start the system up.");
		ts.createIntervalTimer(10000, 5000, tc);
		
		
		// Waits 30 seconds to give time to Grafana to start up.
		mses.schedule(() -> {
			System.out.println("--- Delayed 20 secs, Starting to set Grafana's Home Dashboard");
			GrafanaSetup_All.setHomeDashboard();
		}, 20, TimeUnit.SECONDS);
		
	}
	
	
	
	private Client client = ClientBuilder.newClient();
	
	private WebTarget funcTarget = client.target("http://localhost:8080/RxJavaExample/res/function");
	private WebTarget pearTarget = client.target("http://localhost:8080/RxJavaExample/res/pear");
	private WebTarget majorTarget = client.target("http://localhost:8080/RxJavaExample/res/majorcurrent");
	private WebTarget macroTarget = client.target("http://localhost:8080/RxJavaExample/res/macrohard");
	private WebTarget uptimeTarget = client.target("http://localhost:8080/RxJavaExample/res/uptime");
	private WebTarget rotorTarget = client.target("http://localhost:8080/RxJavaExample/res/rotor");
	
	
	//@Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
	@Timeout
    private void scheduler() {
        
		Container<Double> funcCtn = funcTarget.request().get(new GenericType<Container<Double>>() {});
		Container<Integer> pearCtn = pearTarget.request().get(new GenericType<Container<Integer>>() {});
		Container<Integer> majorCtn = majorTarget.request().get(new GenericType<Container<Integer>>() {});
		Container<Integer> macroCtn = macroTarget.request().get(new GenericType<Container<Integer>>() {});
		Container<Long> uptimeCtn = uptimeTarget.request().get(new GenericType<Container<Long>>() {});
		Container<Integer> rotorCtn = rotorTarget.request().get(new GenericType<Container<Integer>>() {});
		
		rxService.functionNextItem(funcCtn.getValue().doubleValue());
		rxService.pearNextItem(pearCtn.getValue().intValue());
		rxService.majorNextItem(majorCtn.getValue().intValue());
		rxService.macroNextItem(macroCtn.getValue().intValue());
		rxService.uptimeNextItem(uptimeCtn.getValue().longValue());
		rxService.rotorNextItem(rotorCtn.getValue().intValue());
		
		
//		System.out.println(funcCtn);
//		System.out.println(pearCtn);
//		System.out.println(majorCtn);
//		System.out.println(macroCtn);
//		System.out.println(rotorCtn);
		System.out.println("Uptime: " + uptimeCtn.getValue() + " secs.");
		
    }
    
}