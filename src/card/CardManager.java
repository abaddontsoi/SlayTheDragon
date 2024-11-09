
package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardManager {
    private List<ICard> deck;
    private List<ICard> hand;
    private List<ICard> discardedCards;
    private Random random = new Random();
    private int maxHandSize;
    
	public CardManager(List<ICard> deck) {
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.discardedCards = new ArrayList<>();
        this.maxHandSize = 5;
    }
	
	public void drawCard() {
		if (deck.isEmpty()) {
			shuffleDiscardedCards();
		}

		ICard card = deck.remove(0);
		hand.add(card);
	}
	
	public void discardCard(ICard card) {
        hand.remove(card);
        discardedCards.add(card);
    }
	
	public void shuffleDiscardedCards() {
		deck.addAll(discardedCards);
		discardedCards.clear();
		shuffleDeck();
	}
	
	public void shuffleDeck() {
        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            ICard temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }
	
	public List<ICard> getHand() {
		return hand;
	}
	
	public List<ICard> getDeck() {
        return deck;
    }
	
	public List<ICard> getDiscardedCards() {
        return discardedCards;
    }

	public int getMaxHandSize() {
		return maxHandSize;
	}
	
}
