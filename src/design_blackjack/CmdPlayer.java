package design_blackjack;

import java.util.List;
import java.util.Scanner;

public class CmdPlayer extends Player {
	private Scanner in;
	public CmdPlayer(int id) {
		super(id);
		in = new Scanner(System.in);
	}
	@Override
	protected boolean chooseHit(List<List<Card>> otherPlayerInfo, List<Card> dealer) {
		displayCardsInfo(otherPlayerInfo, dealer);
		int needCard = in.nextInt();
		if (needCard == 0) {
			return false;
		}
		return true;
	}
	
	private void displayCardsInfo(List<List<Card>> playerInfo, List<Card> dealer) {
		System.out.println("Players' face-up cards:");
		int id = 1;
		for (List<Card> hand : playerInfo) {
			System.out.print("player[" + id++ + "]: ");
			for (Card card : hand) {
				System.out.print(card.getVal() + " " + card.getSuit() + ";  ");
			}
			System.out.println();
		}
		
		System.out.println("dealer's face-up card: ");
		for (Card card : dealer) {
			System.out.print(card.getVal() + " " + card.getSuit());
		}
		System.out.println();
		
		System.out.println("My id is " + this.getId() + ", cards: ");
		for (Card card : cards) {
			System.out.print(card.getVal() + " " + card.getSuit() + ";  ");
		}
		System.out.println();
		
	}

}
