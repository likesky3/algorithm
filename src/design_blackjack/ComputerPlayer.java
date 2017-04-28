package design_blackjack;

import java.util.List;

public class ComputerPlayer extends Player {
	public ComputerPlayer(int id) {
		super(id);
	}
	
	@Override
	protected boolean chooseHit(List<List<Card>> otherPlayerInfo, List<Card> dealer) {
		int score = calculateScore();
		if (score + 10 < Game.BUST_NUM ) {
			return true;
		} else if (score < 14 && Math.random() < 0.7 || score <= 17 && Math.random() < 0.5) { 
			return true;
		} else {
			return false;
		}
	}
}
