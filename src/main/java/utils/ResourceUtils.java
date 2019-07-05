package utils;

import java.util.concurrent.ThreadLocalRandom;

public class ResourceUtils {
	
	
	public static double sinFunc(double val) {
		
		double comp = 3 * Math.sin( (Math.PI /4) * val ) - 2;
		return comp;
	}
	
	
	public static int checkDirectionEqual() {
		
		int nextInt = ThreadLocalRandom.current().nextInt(9);
		
		if(nextInt > 4) {
			return 5;
		} else if (nextInt < 4) {
			return -5;
		} else {
			return 0;
		}
		
	}	
	
	
	public static int checkDirectionGrowing() {
		
		int nextInt = ThreadLocalRandom.current().nextInt(11);
		
		if(nextInt > 4) {
			return 5;
		} else if (nextInt < 4) {
			return -5;
		} else {
			return 0;
		}
		
	}
	
	
	public static int checkDirectionFalling() {
		
		int nextInt = ThreadLocalRandom.current().nextInt(11);
		
		if(nextInt > 7) {
			return 5;
		} else if (nextInt < 7) {
			return -5;
		} else {
			return 0;
		}
		
	}
}