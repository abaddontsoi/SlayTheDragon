package card;

import java.util.HashMap;
import java.util.Map;


public class CardPool {
	private static CardPool instance;
    private Map<CardName, ICard> cardPrototypes = new HashMap<>();
    
    private CardPool() {
    	CardFactory cardFactory = CardFactory.getInstance();
    	// Iterate through the CardName enum
		for (CardName cardName : CardName.values()) {
			// Create a card object based on the cardName
			//Advance Card Only
			if(cardName.getLevel() == CardName.Level.ADVANCED){
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

    public ICard getCard(CardName name) {
        ICard prototype = cardPrototypes.get(name);
        if (prototype != null) {
            return prototype.clone();
        }
        throw new IllegalArgumentException("Unknown card name: " + name);
    }
    
	public ICard getRandomCard() {
		int randomIndex = (int) (Math.random() * cardPrototypes.size());
		return (ICard) cardPrototypes.values().toArray()[randomIndex];
	}
}
