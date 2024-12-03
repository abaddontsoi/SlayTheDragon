package testings.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.ICard;
import entity.Hand;

class HandTest {
	
	private Hand h;
	

	@BeforeEach
	void setUp() throws Exception {
		h = new Hand();
	}

	@Test
	void testHand() {
		assertNotNull(h.getCards());
	}

	@Test
	void testClearOnEmpty() {
		h.clear();
		assertEquals(0, h.getCards().size());
	}

	@Test
	void testAddCardToHand() {
		// Add some card stubs here
		CardStub card1 = new CardStub();
		CardStub card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		assertEquals(2, h.getCards().size());
	}
	
	@Test
	void testClearOnNonEmpty() {
		// Add some card stubs here
		CardStub card1 = new CardStub();
		CardStub card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		// Clear cards
		h.clear();
		assertEquals(0, h.getCards().size());
	}

	
	@Test
	void testIsEmpty() {
		assertTrue(h.isEmpty());
		
		CardStub card1 = new CardStub();
		CardStub card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		assertFalse(h.isEmpty());
		
		h.clear();
		assertTrue(h.isEmpty());
	}

	@Test
	void testSize() {
		// Add some card stubs here
		CardStub card1 = new CardStub();
		CardStub card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		int result = h.size();
		assertEquals(2, result);
	}

	@Test
	void testGetCard() {
		// Add some card stubs here
		ICard card1 = new CardStub();
		ICard card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		ICard got = h.getCard(0);
		assertEquals(card1, got);
	}


	@Test
	void testRemoveCardFromHand() {
		// Add some card stubs here
		ICard card1 = new CardStub();
		ICard card2 = new CardStub();
		h.addCardToHand(card1);
		h.addCardToHand(card2);

		h.removeCardFromHand(card1);
		assertEquals(1, h.getCards().size());
	}
	
	private class CardStub implements ICard {

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ICard clone() {
			return this;
		}
	}

}
