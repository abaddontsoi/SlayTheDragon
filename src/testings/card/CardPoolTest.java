package testings.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.CardName;
import card.CardPool;
import card.ICard;

class CardPoolTest {

    CardPool pool;
    
    @BeforeEach
    void setUp() throws Exception {
        pool = CardPool.getInstance();
    }

    @Test
    void testGetInstance() {
        assertNotNull(CardPool.getInstance());
    }

    @Test
    void testSingletonInstance() {
        CardPool instance1 = CardPool.getInstance();
        CardPool instance2 = CardPool.getInstance();
        assertSame(instance1, instance2);
    }
    
    @Test
    void testCardPoolOnlyContainsAdvancedCards() {
        int advancedCards = 0;
        for (CardName cardName : CardName.values()) {
            if (cardName.getLevel() == CardName.Level.ADVANCED) {
                advancedCards++;
            }
        }
        assertTrue(advancedCards > 0);
    }

}