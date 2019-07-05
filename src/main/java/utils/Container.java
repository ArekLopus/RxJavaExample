package utils;

// Number not T because of server Json conversion errors.
public class Container<T extends Number> {
	
	private Number value = 0;
	private Number change = 0;
	
	public Container() {}
	
	
	public Container(Number value, Number change) {
		super();
		this.value = value;
		this.change = change;
	}
	
	
	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public Number getChange() {
		return change;
	}

	public void setChange(Number change) {
		this.change = change;
	}


	@Override
	public String toString() {
		return "Container [value=" + value + ", change=" + change + "]";
	}
	
}
