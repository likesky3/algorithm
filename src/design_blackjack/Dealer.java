package design_blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

	@Override
	protected boolean chooseHit(List<List<Card>> otherPlayerInfo, List<Card> dealer) {
		int score = calculateScore();
		if (score < Game.LEAST_SCORE_OF_DEALER) {
			return true;
		}
		return false;
	}
	
	@Override
	List<Card> showMyInitCards() {
		List<Card> initCards = new ArrayList<>();
		for (int i = 0; i < Math.min(cards.size(), 1); i++) {
			initCards.add(cards.get(i));
		}
		return initCards;
	}

}
