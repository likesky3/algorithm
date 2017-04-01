package design;

public enum VehicleSize {
	COMPACT(10), 
	NORMAL(20), 
	TRUCK(30),
	DISABLED(40);
	
	private final int size;
	VehicleSize(int size) { // ><
		this.size = size;
	}
	public int getSize() {
		return size;
	}
}
