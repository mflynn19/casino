import java.util.ArrayList;
import java.util.Collections;

//cut out all extra stuff from AP lab
/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck extends ArrayList<Card>{
	
	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(String[] suits, String[] ranks, int[] values) {
		for (int i = 0; i < ranks.length; i++) {
			for (String suitString : suits) {
				this.add(new Card(ranks[i], suitString, values[i]));
			}
		}
		Collections.shuffle(this);
	}

	/**
	 * Deals a card from this deck.
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if(this.size() > 0) {
			return this.remove(0);
		}
		return null;
	}
}

