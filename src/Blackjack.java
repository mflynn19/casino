import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Blackjack {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Casino!");
		System.out.println("You've been selected to play Blackjack against our world renowned Dealer!");
		System.out.println("What is your name for this game?");
		String playerName = in.nextLine();	
		System.out.println("As a sign of goodwill from the Dealer " + playerName + ", here are 100 free credits to bet.");
		
		int chips = 100;
		do {
			int bet = 0;
			do {	
				bet = Integer.parseInt(question("What are you willing to wager?", "\\d+", in));	
			} while (bet > chips || bet <= 0);
			
			if(loseYourMoney()) {
				chips += bet;
			}
			else {
				chips -= bet;
			}
			System.out.println();
			if(chips == 0) {
				System.out.println("You've run out of chips! You can't continue, even if you'd want to.");
			}
		} while(chips != 0 && question("Would you like to keep playing (continue), or cash out (moolah)?", Arrays.asList("continue", "moolah"), in).equals("continue"));
	}
	
	public static boolean loseYourMoney() {
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
						"Ten", "King", "Queen", "Jack", "Ace" };
		int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
		
		Deck deck = new Deck(suits, ranks, values);
		Hand dealer = new Hand();
		Hand user = new Hand();
		
		//deal initial cards
		dealer.add(deck.deal());
		dealer.add(deck.deal());
		user.add(deck.deal());
		user.add(deck.deal());

		//check in
		System.out.println("Your Hand: " + user);
		System.out.println("Dealer's Hand: " + dealer.get(0));
		
		//Player's turn
		String doing = "";
		while (user.score() != 0 && !doing.equals("stand")) {
			System.out.println("Would like to hit or stand?");
			doing = in.nextLine().toLowerCase();
			if (doing.equals("hit")) {
				user.add(deck.deal());
				System.out.println("Here's what Lady Luck gave you!" + user);
				System.out.println("");
			} 
		} 	
		//bust
		if(user.score() == 0) {
			System.out.println("You busted... Better luck next time!");
			return false;
		}
		System.out.println("Your Final Hand: " + user.score());

		
		//Dealer's turn
		System.out.println("Now watch our dealer do some magic...");
		while(dealer.score() <= 17 && dealer.score() != 0) {
			dealer.add(deck.deal());
		}
		
		//check if perfect Blackjack
		if(dealer.score() == 21) {
			System.out.println("Our Dealer is victorious again. Learn how to play before challenging the master.");
			return false;
		}
		//check bust 
		if(dealer.score() == 0) {
			System.out.println("Our Dealer is having a rough night, somehow you won. It won't happen next time...");
			return true;
		} 
		
		System.out.println("Dealer's Final Hand: " + dealer.score());
		
		//else normally compare them
		int result = user.compareTo(dealer);
		//+1 for greater than (user win), -1 for less than (dealer win), and zero for equal to (dealer wins just because)
		if(result == 1) {
			System.out.println("Our Dealer is having a rough night, somehow you won. It won't happen next time..."); 
			return true;
		} 
		else {
			System.out.println("Our Dealer is victorious again. Learn how to play before challenging the master.");
			return false;
		}
	}
	public static String question(String q, List<String> answers, Scanner in) {
		String response = "";
		do {
			System.out.println(q);
			response = in.nextLine().toLowerCase();
		} while(answers.indexOf(response) == -1);
		return response;
	}
	
	public static String question(String q, String format, Scanner in) {
		String response = "";
		do {
			System.out.println(q);
			response = in.nextLine();
		} while(!response.matches(format));
		return response;
	}
}