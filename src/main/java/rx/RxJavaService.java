package rx;

import javax.enterprise.context.ApplicationScoped;

import io.reactivex.subjects.PublishSubject;

@ApplicationScoped
public class RxJavaService {
	
	private PublishSubject<Double> functionSubject = PublishSubject.create();
	private PublishSubject<Integer> pearSubject = PublishSubject.create();
	private PublishSubject<Integer> majorSubject = PublishSubject.create();
	private PublishSubject<Integer> macroSubject = PublishSubject.create();
	private PublishSubject<Long> uptimeSubject = PublishSubject.create();
	private PublishSubject<Integer> rotorSubject = PublishSubject.create();
	
	
	public void functionNextItem(Double item) {
		functionSubject.onNext(item);
	}
	
	public void pearNextItem(Integer item) {
		pearSubject.onNext(item);
	}
	
	public void majorNextItem(Integer item) {
		majorSubject.onNext(item);
	}
	
	public void macroNextItem(Integer item) {
		macroSubject.onNext(item);
	}
	
	public void uptimeNextItem(Long item) {
		uptimeSubject.onNext(item);
	}
	
	public void rotorNextItem(Integer item) {
		rotorSubject.onNext(item);
	}
	
	
	
	public PublishSubject<Double> getFunctionSubject() {
		return functionSubject;
	}
	
	public PublishSubject<Integer> getPearSubject() {
		return pearSubject;
	}
	
	public PublishSubject<Integer> getMajorSubject() {
		return majorSubject;
	}
	
	public PublishSubject<Integer> getMacroSubject() {
		return macroSubject;
	}
	
	public PublishSubject<Long> getUptimeSubject() {
		return uptimeSubject;
	}
	
	public PublishSubject<Integer> getRotorSubject() {
		return rotorSubject;
	}
}
