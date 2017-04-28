package design_parkinglot;

public class ParkingTicket {
	private static final float FEE_EACH_MINUTE = 0.05F; 
	private final long start; // start minute
	private final int spotId;
	
	public ParkingTicket(int spotId) {
		this.start = System.currentTimeMillis();
		this.spotId = spotId;
	}
	
	float calculateFee() {
		return FEE_EACH_MINUTE * ((System.currentTimeMillis() - start) / 60000);
	}
}
