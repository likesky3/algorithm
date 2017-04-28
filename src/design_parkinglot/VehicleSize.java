package design_parkinglot;

public enum VehicleSize {
	COMPACT(10), 
	NORMAL(20), 
	TRUCK(30),
	DISABLED(40);
	
	private final int size;
	VehicleSize(int size) { // >< enum 
		this.size = size;
	}
	public int getSize() {
		return size;
	}
}
