package testings.card;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import battle.ComputeCenter;
import card.CardManager;
import card.ICard;
import card.skill.DrawCard;
import entity.Entity;
import entity.Foe;
import entity.Player;

class CardManagerTest {

    CardManager manager;
    Entity entity;
    
    private static final String INITIAL_ENTITY_NAME = "Entity";
    private static final double INITIAL_MAX_HEALTH = 100;
    private static final int INITIAL_DEFENSE = 10;
    private static final int INITIAL_STRENGTH = 20;
    
    @BeforeEach
    void setUp() throws Exception {
        List<ICard> initDeck = new ArrayList<>();
        entity = new Entity(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, initDeck) {

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return INITIAL_ENTITY_NAME;
            }
            
        };
        manager = new CardManager(initDeck, null);
    }

    @Test
    void testDrawCardEmptyDeck() {
        assertThrows(NoSuchElementException.class, () -> manager.drawCard());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 5, 10})
    void testInitialDeckSize(int size) {
        List<ICard> deck = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            deck.add(new ICard() {
                @Override
                public String getDescription() {
                    return "Test Card";
                }

                @Override
                public String getName() {
                    return "Test Card ";
                }
                
                @Override
                public ICard clone() {
                	return this;
                }
            });
        }
        CardManager testManager = new CardManager(deck, entity);
        assertDoesNotThrow(() -> testManager.drawCards());
    }

    @Test
    void testDiscardAndReplenish() {
        List<ICard> deck = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            deck.add(new ICard() {
                @Override
                public String getDescription() {
                    return "Test Card";
                }

                @Override
                public String getName() {
                    return "Test Card ";
                }
                
                @Override
                public ICard clone() {
                	return this;
                }
            });
        }
        CardManager testManager = new CardManager(deck, entity);
        
        // Draw and discard all cards
        ICard card1 = testManager.drawCard();
        ICard card2 = testManager.drawCard();
        ICard card3 = testManager.drawCard();
        
        testManager.discardCard(card1);
        testManager.discardCard(card2);
        testManager.discardCard(card3);
        
        // Draw again - should get replenished cards
        assertNotNull(testManager.drawCard());
    }
    
    @Test
    void testInitializeTurn() {
        List<ICard> deck = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            deck.add(new ICard() {
                @Override
                public String getDescription() {
                    return "Test Card";
                }

                @Override
                public String getName() {
                    return "Test Card ";
                }
                
                @Override
                public ICard clone() {
                	return this;
                }
            });
        }
        CardManager testManager = new CardManager(deck, entity);
        testManager.initializeTurn();

        assertThrows(NoSuchElementException.class, () -> {
            // Should have drawn initial hand of cards
            int remainingCards = 0;
            while(testManager.drawCard() != null) {
                remainingCards++;
            }
        });
    }
}