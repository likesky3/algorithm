package design_blackjack;

import java.util.*;

import org.junit.Assert;

public class Game {
	public static final int BUST_NUM = 22;
	public static final int LEAST_SCORE_OF_DEALER = 17;
	private final Deck deck;
	private Player[] computerPlayers;
	private Player[] cmdPlayers;
	private Player dealer;

	List<List<Card>> playersInitCards;

	public Game() {
		deck = new Deck();
	}

	public void play(int numOfComputerPlayer, int numOfCmdPlayer) {
		Assert.assertTrue(numOfComputerPlayer + numOfCmdPlayer < 8);

		initGame(numOfComputerPlayer, numOfCmdPlayer);

		// player draw card from deck
		int[] finishedNum = { 0 };
		int numOfPlayers = numOfComputerPlayer + numOfCmdPlayer;
		while (finishedNum[0] < numOfPlayers) {
			playOneRound(computerPlayers, finishedNum);
			playOneRound(cmdPlayers, finishedNum);
		}

		// dealer draw cards
		while (dealer.playThisTurn(playersInitCards, null)) {
			dealer.addCards(deck.dealtCards(1));
		}

		System.out.println("=====Everybody face up your cards=====");
		printAllCards();

		int dealerScore = dealer.calculateScore();
		if (dealerScore >= BUST_NUM) {
			dealerScore = -1;
		}
		List<Player> winCandidates = new ArrayList<>();
		int[] maxScore = { dealerScore };
		calculateScores(winCandidates, maxScore, computerPlayers);
		calculateScores(winCandidates, maxScore, cmdPlayers);
		if (winCandidates.isEmpty()) {
			System.out.println("Dealer win.");
		} else if (maxScore[0] == dealerScore) {
			System.out.println("Tie.");
		} else {
			System.out.println("Dealer loss, winner ids are: ");
			for (Player player : winCandidates) {
				System.out.println(player.getId());
			}
		}

	}

	private void initGame(int numOfComputerPlayer, int numOfCmdPlayer) {
		int id = 1;
		computerPlayers = new ComputerPlayer[numOfComputerPlayer];
		for (int i = 0; i < numOfComputerPlayer; i++) {
			computerPlayers[i] = new ComputerPlayer(id++);
		}
		cmdPlayers = new CmdPlayer[numOfCmdPlayer];
		for (int i = 0; i < numOfCmdPlayer; i++) {
			cmdPlayers[i] = new CmdPlayer(id++);
		}
		dealer = new Dealer();

		deck.shuffle();

		dealtInitCards(computerPlayers);
		dealtInitCards(cmdPlayers);
		dealtInitCards(dealer);

		playersInitCards = getInitCardsOfPlayers();
	}

	private void dealtInitCards(Player[] players) {
		for (Player player : players) {
			player.addCards(deck.dealtCards(2));
		}
	}

	private void dealtInitCards(Player player) {
		player.addCards(deck.dealtCards(2));
	}

	private List<List<Card>> getInitCardsOfPlayers() {
		List<List<Card>> result = new ArrayList<>();
		for (Player other : computerPlayers) {
			result.add(other.showMyInitCards());
		}
		for (Player other : cmdPlayers) {
			result.add(other.showMyInitCards());
		}
		return result;
	}

	private void calculateScores(List<Player> winCandidates, int[] maxScore, Player[] players) {
		for (Player player : players) {
			int currScore = player.calculateScore();
			if (currScore >= BUST_NUM) {
				continue;
			}
			if (currScore > maxScore[0]) {
				maxScore[0] = currScore;
				winCandidates.clear();
				winCandidates.add(player);
			} else if (currScore == maxScore[0]) {
				winCandidates.add(player);
			}
		}
	}

	private void playOneRound(Player[] players, int[] finishedNum) {
		for (Player player : players) {
			boolean wantACard = player.playThisTurn(playersInitCards, dealer.showMyInitCards());
			if (wantACard) {
				player.addCards(deck.dealtCards(1));
			} else {
				finishedNum[0]++;
			}
		}
	}

	private void printAllCards() {
		System.out.println("Deal's cards: ");
		System.out.println(dealer);

		System.out.println("\nAll players: ");
		for (Player p : computerPlayers) {
			System.out.println(p);
		}

		for (Player p : cmdPlayers) {
			System.out.println(p);
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		while (true) {
			game.play(3, 1);
			System.out.println("\n\n\n=======================");
		}
	}
}
