package entity;

import java.util.*;

import card.ICard;
import effect.EffectInTurns;

public class Player extends Entity {
	
	

	public Player(double maxHealth, double defense, double strength, List<ICard> deck) {
		super(maxHealth, defense, strength, deck);
	}
	
	public void initializeTurn() {
		// Draw a card from the deck until the hand is full
		while (cardManager.getHand().size() < cardManager.getMaxHandSize()) {
            cardManager.drawCard();
		}
	}

	@Override
	public void chooseCard(Entity opponent) {
		// Check if the hand is empty
	    if (cardManager.getHand().isEmpty()) {
	        System.out.println("No cards in hand.");
	        return;
	    }
	    
		// Prompt the player to choose a card
		ICard chosenCard = gameIO.promptCardSelection(getHand());
		// Use the chosen card
		chosenCard.use(this, opponent);
		cardManager.discardCard(chosenCard);
		// Display the chosen card
		gameIO.displayMessage("The player used " + chosenCard.getName());
	}
	
	public String getName() {
		return "Player";
	}

}
