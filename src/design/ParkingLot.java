package design;

import java.util.*;

public class ParkingLot {
	private List<Level> levels;
	
	public ParkingLot(int levelNum, int spotsNumInEachLevel) {
		levels = new ArrayList<>(levelNum);
		for (int i = 0; i < levelNum; i++) {
			levels.add(new Level(spotsNumInEachLevel));
		}
	}
	
	public boolean hasSpot(Vehicle v) { 
		for (Level level : levels) {
			if (level.hasSpot(v)) {
				return true;
			}
		}
		return false;
	}
	
	public ParkingTicket park(Vehicle v) {
		ParkingTicket t = null;
		for (Level level : levels) {
			if ((t = level.park(v)) != null) {
				return t;
			}
		}
		return null;
	}
	
	public float leave(Vehicle v, ParkingTicket t) {
		boolean findVehicle = false;
		for (Level level : levels) {
			if (level.unpark(v)) {
				findVehicle = true;
				break;
			}
		}
		
		if (findVehicle && (v.doNotNeedTicket() || t != null)) {
			return t.calculateFee();
		} 
		return 0;
	}
}
