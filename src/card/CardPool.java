package card;

import java.util.HashMap;
import java.util.Map;

import gameIO.GameIO;
import gameIO.IIOHandler;

public class CardPool {
	private static CardPool instance;
	private Map<CardName, ICard> cardPrototypes = new HashMap<>();

	private CardPool() {
		CardFactory cardFactory = CardFactory.getInstance();
		// Iterate through the CardName enum
		for (CardName cardName : CardName.values()) {
			// Create a card object based on the cardName
			// Advance Card Only
			if (cardName.getLevel() == CardName.Level.ADVANCED) {
				ICard card = cardFactory.createCard(cardName);
				// Add the card to the cardPrototypes map
				cardPrototypes.put(cardName, card);
			}
		}
	}

	public static CardPool getInstance() {
		if (instance == null) {
			instance = new CardPool();
		}
		return instance;
	}

	public ICard getRwardCard(GameIO gameIO) {
		// int randomIndex = (int) (Math.random() * cardPrototypes.size());
		int index = -1;
		int i = 1;
		gameIO.displayMessage("Normal Reward: (Choose one of the reward below)");
		for (Map.Entry<CardName, ICard> entry : cardPrototypes.entrySet()) {
			ICard card = entry.getValue();
			gameIO.displayMessage(i + ". " + card.getName() + " - " + card.getDescription());
			i++;
		}

		while (index < 1 || index > cardPrototypes.size()) {
			gameIO.displayMessage("Enter the number of your choice:");
			try {
				index = Integer.parseInt(gameIO.getInput());
			} catch (NumberFormatException e) {
				gameIO.displayMessage("Invalid input. Please enter a number.");
			}
		}
		return (ICard) cardPrototypes.values().toArray()[index - 1];
	}
}
