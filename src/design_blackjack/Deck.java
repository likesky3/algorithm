package design_blackjack;

import org.junit.Assert;

enum Suit {
	Spades("\u2764"),
	Clubs("\u2663"),
	Diamonds("\u2666"),
	Hearts("\u2661");
	
	private String utf8Val;
	private Suit(String utf8Val) {
		this.utf8Val = utf8Val;
	}
	
	public String toString() {
		return utf8Val;
	}
}

class Card {
	private String val;
	private Suit suit;
	public Card(String val, Suit suit) {
		this.val = val;
		this.suit = suit;
	}
	
	public String getVal() {
		return val;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String toString() {
		return val + " " + suit; 
	}
}

public class Deck {
	private Card[] cards;
	private int curr = 0; // the current top of the deck
	
	public Deck() {
		cards = new Card[52];
		int k = 0;
		for (Suit suit : Suit.values()) {
			for (int i = 2; i <= 10; i++) {
				cards[k++] = new Card(String.valueOf(i), suit);
			}

			cards[k++] = new Card("J", suit);
			cards[k++] = new Card("Q", suit);
			cards[k++] = new Card("K", suit);
			cards[k++] = new Card("A", suit);
		}
	}
	
	public void shuffle() {
		for (int i = cards.length; i > 0; i--) {
			int j = (int)(Math.random() * i);
			swap(cards, j, i - 1);
		}
		curr = 0;
	}
	
	public Card[] dealtCards(int n) {
		Assert.assertFalse(n < 0 || n > (cards.length - curr));
		Card[] ret = new Card[n];
		for (int i = 0; i < n; i++) {
			ret[i] = cards[curr++];
		}
		return ret;
	}
	
	private void swap(Card[] cards, int i, int j) {
		Card tmp = cards[i];
		cards[i] = cards[j];
		cards[j] = tmp;
	}
}
