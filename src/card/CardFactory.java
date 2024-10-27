package card;

import java.util.ArrayList;
import java.util.List;

import card.attack.*;
import card.defend.*;
import card.skill.*;

public class CardFactory {

	 public static ICard createCard(String cardType) {
	    switch (cardType.toLowerCase()) {
	        case "basicattackcard":
	            return new BasicAttackCard(); // Example attack value
	        case "basicdefendcard":
	            return new BasicDefendCard(); // Example block value
//	        case "enhanceattack":
//	            return new SpecialEffectCard(new EnhanceNextAttackEffect(5));
	        case "basicpoisoncard":
	            return new BasicPoisonCard(); // Example poison value
	        // Extend with more cases for different effects
	        default:
	            throw new IllegalArgumentException("Unknown card type: " + cardType);
	    }
    }
	 
	public static ICard createRandomCard() {
		String[] cardTypes = { "basicattackcard", "basicdefendcard", "basicpoisoncard" };
		return createCard(cardTypes[(int) (Math.random() * cardTypes.length)]);
	}
	
	public static List<ICard> createRandomDeck() {
		List<ICard> deck = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			deck.add(createRandomCard());
		}
		return deck;
	}

}
