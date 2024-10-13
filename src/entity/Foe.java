package entity;

import java.util.List;

import card.ICard;

public class Foe extends Entity {
	private String name;

	public Foe(String name, double maxHealth, double defense, double strength, List<ICard> deck) {
		super(maxHealth, defense, strength, deck);
		this.name = name;
		// TODO Auto-generated constructor stub
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
	
	    // Choose a card randomly
	    int randomIndex = (int) (Math.random() * cardManager.getHand().size());
	    ICard chosenCard = cardManager.getHand().get(randomIndex);
	
	    // Use the chosen card
	    chosenCard.use(this, opponent);
	    cardManager.discardCard(chosenCard);
	
	    // Display the chosen card
	    System.out.println(name + " used " + chosenCard.getName());
	}


	public String getName() {
		return name;
	}
}
