package entity;

import java.util.ArrayList;
import java.util.List;

import card.ICard;

public class Hand {
	private List<ICard> cards;
	
	public Hand() {
		this.cards = new ArrayList<>();
	}
	
	public void clear() {
		cards.clear();
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public int size() {
		return cards.size();
	}
	
	public List<ICard> getCards() {
		return cards;
	}
	
	public ICard getCard(int index) {
		return cards.get(index);
	}
	
	public void addCardToHand(ICard card) {
		cards.add(card);
	}
	
	public void removeCardFromHand(ICard card) {
		cards.remove(card);
	}
}
