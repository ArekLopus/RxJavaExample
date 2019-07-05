package metrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import io.reactivex.schedulers.Schedulers;
import rx.RxJavaService;

@ApplicationScoped
public class MetricsService {
	
	@Inject
	RxJavaService rjs;
	
	private double functionValue = 0;
	private double pearValue = 1000;
	private double majorValue = 8000;
	private double macroValue = 5000;
	private long uptimeValue = 0;
	private int rotorValue = 3500;
	
	
	@Gauge(unit = MetricUnits.NONE, name="Function Gauge", absolute = true)
	public double functionGauge() {
		return functionValue;
	}
	
	@Gauge(unit = "price", name="Pear Gauge", absolute = true)
	public double pearGauge() {
		return pearValue;
	}
	
	@Gauge(unit = "price", name="MajorCurrent Gauge", absolute = true)
	public double majorGauge() {
		return majorValue;
	}
	
	@Gauge(unit = "price", name="Macrohard Gauge", absolute = true)
	public double macroGauge() {
		return macroValue;
	}
	
	@Gauge(unit = MetricUnits.SECONDS, name="Uptime Gauge", absolute = true)
	public double uptimeGauge() {
		return uptimeValue;
	}
	
	@Gauge(unit = "rpm", name="Rotor Gauge", absolute = true)
	public double rotorGauge() {
		return rotorValue;
	}
	
	
	// Auto init at startup
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		
		rjs.getFunctionSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setFunctionValue(d);
			});
		
		rjs.getPearSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setPearValue(d);
			});
		
		rjs.getMajorSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setMajorValue(d);
			});
		
		rjs.getMacroSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setMacroValue(d);
			});
		
		rjs.getUptimeSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setUptimeValue(d);
			});
		
		rjs.getRotorSubject()
			.observeOn(Schedulers.computation())
			.subscribe(d -> {
				this.setRotorValue(d);
			});
		
	}
	
	
	public double getPearValue() {
		return pearValue;
	}

	public void setPearValue(double pearValue) {
		this.pearValue = pearValue;
	}

	public double getFunctionValue() {
		return functionValue;
	}

	public void setFunctionValue(double functionValue) {
		this.functionValue = functionValue;
	}

	public double getMajorValue() {
		return majorValue;
	}

	public void setMajorValue(double majorValue) {
		this.majorValue = majorValue;
	}

	public double getMacroValue() {
		return macroValue;
	}

	public void setMacroValue(double macroValue) {
		this.macroValue = macroValue;
	}

	public long getUptimeValue() {
		return uptimeValue;
	}

	public void setUptimeValue(long uptimeValue) {
		this.uptimeValue = uptimeValue;
	}

	public int getRotorValue() {
		return rotorValue;
	}

	public void setRotorValue(int rotorValue) {
		this.rotorValue = rotorValue;
	}
	
}
