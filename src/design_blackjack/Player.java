package design_blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private final int id;
	private boolean isFinished = false;
	
	protected List<Card> cards;
	
	public Player(int id) {
		this.id = id;
		cards = new ArrayList<>();
	}
	
	public Player() {
		this(0);
	}
	
	public int getId() {
		return id;
	}
	protected abstract boolean chooseHit(List<List<Card>> otherPlayerInfo, List<Card> dealer);
	
	boolean playThisTurn(List<List<Card>> otherPlayerInfo, List<Card> dealer) {
		if (!isFinished ) {
			if (isBusting() || !chooseHit(otherPlayerInfo, dealer)) {
				isFinished = true;
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	boolean isFinished() {
		return isFinished;
	}
	
	boolean isBusting() {
		return calculateScore() >= Game.BUST_NUM;
	}
	
	int calculateScore() {
		int scores = 0;
		int numOfAs = 0;
		for (Card card : cards) {
			if ("A".equals(card.getVal())) {
				numOfAs++;
			} else {
				try {
					scores +=  Integer.valueOf(card.getVal());
				} catch (Exception e) {
					scores += 10;
				}
			}
		}
		scores += numOfAs;
		if (numOfAs > 0 && scores + 10 < Game.BUST_NUM) {
			scores += 10;
		}
		return scores;
	}
	
	void addCards(Card[] newCards) {
		for (Card card : newCards) {
			cards.add(card);
		}
	}
	
	List<Card> showMyInitCards() {
		List<Card> initCards = new ArrayList<>();
		for (int i = 0; i < Math.min(cards.size(), 2); i++) {
			initCards.add(cards.get(i));
		}
		return initCards;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Player[").append(id).append("], scores=").append(calculateScore()).append(": ");
		for (Card card : cards) {
			sb.append(card);
		}
		return sb.toString();
	}
}
