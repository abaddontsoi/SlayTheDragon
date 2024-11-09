package entity;

import java.util.ArrayList;
import java.util.List;

import card.ICard;

public class Foe extends Entity {
	private String name;
	private String type;

	public Foe(String name, String type, double maxHealth, double defense, double strength, List<ICard> deck) {
		super(maxHealth, defense, strength, deck);
		this.type = type;
		this.name = name;
	}

	public void initializeTurn() {
		cardManager.drawCards(this);
	}

	public String getType() {
		return type;
	}

	@Override
	public List<ICard> chooseCards() {
		List<ICard> chosenCards = new ArrayList<>();
		// Check if the hand is empty
		if (chosenCards.isEmpty()) {
			cardManager.drawCards(this);
		}

		// Choose the first CHOSEN_CARDS_SIZE cards
		// put them in the chosen cards and discard them
		for (int i = 0; i < CHOSEN_CARDS_SIZE; i++) {
			ICard chosenCard = hand.getCard(i);
			chosenCards.add(chosenCard);
			hand.removeCardFromHand(chosenCard);
			cardManager.discardCard(chosenCard);
		}

		// Put rest of the hand back to the discarded cards
		for (ICard card : hand.getCards()) {
			cardManager.discardCard(card);
		}

		// Clear the hand
		hand.clear();

		return chosenCards;
	}

	public String getName() {
		return name;
	}
}
