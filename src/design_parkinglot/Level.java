package design_parkinglot;

import java.util.*;

class Level {
	private static final float NORMAL_RATIO = 0.5f;
	private static final float TRUCK_RATIO = 0.1f;
	private static final float DISABLED_RATIO = 0.1f;
	
	private final Set<ParkingSpot> availableSpots; // >< use final as long as you can
	private final Set<ParkingSpot> occupiedSpots; // >< use final
	
	Level(int spotsNum) {
		availableSpots = new HashSet<>(spotsNum);
		int normalNum = (int)(NORMAL_RATIO * spotsNum);
		int truckNum = (int)(TRUCK_RATIO * spotsNum);
		int disabledNum = (int)(DISABLED_RATIO * spotsNum);
		int compactNum = spotsNum - normalNum - truckNum - disabledNum;
		
		int id = 0;
		for (int i = 0; i < normalNum; i++) {
			availableSpots.add(new ParkingSpot(VehicleSize.NORMAL, id++));
		}
		for (int i = 0; i < truckNum; i++) {
			availableSpots.add(new ParkingSpot(VehicleSize.TRUCK, id++));
		}
		for (int i = 0; i < disabledNum; i++) {
			availableSpots.add(new ParkingSpot(VehicleSize.DISABLED, id++));
		}
		for (int i = 0; i < compactNum; i++) {
			availableSpots.add(new ParkingSpot(VehicleSize.COMPACT, id++));
		}
		occupiedSpots = new HashSet<>(spotsNum);
		
		// >< if just use a single ArrayList to store all spots, 
		// we can make spots unmodifiable
	}
	
	boolean hasSpot(Vehicle v) {
		for (ParkingSpot s : availableSpots) {
			if (s.fit(v)) {
				return true;
			}
		}
		return false;
	}
	
	ParkingTicket park(Vehicle v) {
		ParkingTicket t = null;
		ParkingSpot parkedSpot = null;
		for (ParkingSpot s : availableSpots) {
			if ((t = s.park(v)) != null) {
				parkedSpot = s;
				break;
			}
		}
		if (parkedSpot != null) {
			availableSpots.remove(parkedSpot);
			occupiedSpots.add(parkedSpot);
		}
		return t;
	}
	
	boolean unpark(Vehicle v) {
		ParkingSpot parkedSpot = null;
		for (ParkingSpot s : occupiedSpots) {
			if (s.getVehicle() == v) {
//			if (s.unpark(v)) {
				s.leave();
				parkedSpot = s;
				break;
			}
		}
		if (parkedSpot != null) {
			availableSpots.add(parkedSpot);
			occupiedSpots.remove(parkedSpot);
			return true;
		}
		return false;
	}

}
