import java.util.ArrayList;

public class Hand extends ArrayList<Card> implements Comparable<Hand>{
	public Hand() {
		
	}
	public int score() {
		int points = 0;
		boolean hasAce = false;
		for(Card tarjeta : this) {
			points += tarjeta.getValue();
			if(tarjeta.getRank() == "ace") {
				hasAce = true;
			}
		}
		if (points > 21) {
			if(hasAce) {
				points -= 10;
			} else {
				points = 0;
			}
		}
		return points;
	}
	public int compareTo(Hand other) {
		int userScore = this.score();
		int dealerScore = other.score();
		if(userScore == dealerScore) {
			return 0;
		} 
		else if(userScore > dealerScore) {
			return 1;
		} 
		else {
			return -1;
		}
	}
	@Override
	public String toString() {
		String s = "";
		for(Card c : this) {
			s += c;
			s += "\n";
		}
		return s;
	}
	
}