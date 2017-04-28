package design_parkinglot;

class ParkingSpot {
	private int id;
	private final VehicleSize size; // >< use final as long as you can
	private Vehicle currVehicle;
	
	ParkingSpot(VehicleSize size, int id) { 
		this.size = size;
		this.id = id;
	}
	
	boolean fit(Vehicle v) {
//		return size.getSize() >= v.size.getSize(); // >< not enough
		return currVehicle == null && size.getSize() >= v.size.getSize();
	}
	
	ParkingTicket park(Vehicle v) {
		if (!fit(v)) {
			return null;
		}
		currVehicle = v;
		ParkingTicket t = new ParkingTicket(id);
		return t;
	}
	
	// >< use leave() & getVehicle() instead of unpark()
//	boolean unpark(Vehicle v) {
//		return currVehicle == v; 
//	}
	void leave() {
		currVehicle = null;
	}
	
	Vehicle getVehicle() {
		return currVehicle;
	}
}
