package design_parkinglot;

public class Vehicle {
	final VehicleSize size;
	private boolean needTicket;
	public Vehicle (VehicleSize size, boolean needTicket) {
		this.size = size;
		this.needTicket = needTicket;
	}
	
	boolean doNotNeedTicket() {
		return !needTicket;
	}
}
