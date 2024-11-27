package entity;

import java.util.*;

import card.ICard;

public class Player extends Entity {

	public Player(double maxHealth, int defense, int strength, List<ICard> deck) {
		super(maxHealth, defense, strength, deck);
	}
	
	// public void initializeTurn() {
	// 	cardManager.drawCards(this);
	// }

	// @Override
	// public List<ICard> chooseCards() {
	// 	List<ICard> chosenCards = new ArrayList<>();
	// 	// Check if the hand is empty
	// 	if (hand.isEmpty()) {
	// 		cardManager.drawCards(this);
	// 	}
  
	// 	// Prompt the player to choose CHOSEN_CARDS_SIZE cards
	// 	for (int i = 0; i < CHOSEN_CARDS_SIZE; i++) {
	// 		ICard chosenCard = gameIO.promptCardSelection(hand.getCards());
	// 		// add the chosen card to the chosen cards
	// 		chosenCards.add(chosenCard);
	// 		// remove the chosen card from the hand
	// 		// and put it in the discard pile
	// 		hand.removeCardFromHand(chosenCard);
	// 		cardManager.discardCard(chosenCard);
	// 	}
		
	// 	// Put rest of the hand back to the discarded cards
	// 	for (ICard card : hand.getCards()) {
	// 		cardManager.discardCard(card);
	// 	}
		
	// 	// Clear the hand
	// 	hand.clear();
		
	// 	return chosenCards;
	// }
	
	public String getName() {
		return "Player";
	}

	// public void addCardToDeck(ICard card) {
	// 	cardManager.addCardToDeck(card);
	// }


}
