
package card;

import java.util.*;

import entity.Entity;

public class CardManager {
    private Deque <ICard> deck;
    private List<ICard> discardPile;
    private Random random;
    private static final int INITIAL_HAND_SIZE = 5;

    public CardManager(List<ICard> initialDeck) {
        this.deck = new ArrayDeque<>(initialDeck);
        this.discardPile = new ArrayList<>();
        this.random = new Random();
    }

    public void drawCards(Entity entity) {
         while (entity.getHand().size() < INITIAL_HAND_SIZE) {
            entity.addCardToHand(drawCard());
         }
    }
    
	public ICard drawCard() {
		if (deck.isEmpty()) {
			replenishDeck();
		}
		return deck.pop();
	}
			
	public void discardCard(ICard card) {
		discardPile.add(card);
	}


    private void replenishDeck() {
		Collections.shuffle(discardPile, random);
		deck.addAll(discardPile);
		discardPile.clear();
    }

	public void addCardToDeck(ICard card) {
		deck.add(card);	
	}
}
