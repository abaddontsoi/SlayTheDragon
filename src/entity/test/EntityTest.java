package entity.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import card.ICard;
import effect.EffectInTurns;
import entity.Entity;
import entity.EntityStatus;

import java.util.*;

class EntityTest {
	private TestEntity mockEntity;
    private List<ICard> testDeck;
    private static final double DELTA = 0.001;
	private static final double INITIAL_MAX_HEALTH = 100;
	private static final int INITIAL_DEFENSE = 0;
	private static final int INITIAL_STRENGTH = 0;
	
	private static final String MOCK_ENTITY_NAME = "Mock Entity";

	private static final double TEST_DAMAGE = 10;
	private static final double TEST_INCREASE_DEFENSE = 20;
	private static final double TEST_DECREASE_DEFENSE = 10;
	private static final double TEST_INCREASE_STRENGTH = 5;
	
    private class TestEntity extends Entity {
        public TestEntity(double maxHealth, int defense, int strength, List<ICard> deck) {
            super(maxHealth, defense, strength, deck);
        }

        @Override
        public String getName() {
            return MOCK_ENTITY_NAME;
        }
    }
    
	@BeforeEach
	void setUp() throws Exception {
        testDeck = new ArrayList<>();
		mockEntity = new TestEntity(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, testDeck);
	}

	@Test
	void testGetName() {
		String result = mockEntity.getName();
		assertEquals(MOCK_ENTITY_NAME, result);
	}
	
    @Test
    void testConstructor() {
        assertEquals(INITIAL_MAX_HEALTH, mockEntity.getMaxHealth(), DELTA);
        assertEquals(INITIAL_MAX_HEALTH, mockEntity.getHealth(), DELTA);
        assertEquals(INITIAL_DEFENSE, mockEntity.getDefense(), DELTA);
        assertEquals(INITIAL_STRENGTH, mockEntity.getStrength(), DELTA);
        assertTrue(mockEntity.getEffects().isEmpty());
        assertEquals(MOCK_ENTITY_NAME, mockEntity.getName());
    }
    
    @Test
    void testDeckOperations() {
        TestCard card1 = new TestCard();
        TestCard card2 = new TestCard();

        mockEntity.addCardToDeck(card1);
        mockEntity.addCardToDeck(card2);

        List<ICard> deck = mockEntity.getDeck();
        assertEquals(2, deck.size());
        assertTrue(deck.contains(card1));
        assertTrue(deck.contains(card2));

        // Verify deck clone
        List<ICard> originalDeck = mockEntity.getDeck();
        List<ICard> clonedDeck = mockEntity.getDeck();
        assertNotSame(originalDeck, clonedDeck);
    }

    @Test
    void testEntityStatus() {
        EntityStatus status = mockEntity.getEntityStatus();
        assertNotNull(status);
        assertEquals(mockEntity.getHealth(), status.getHealth(), DELTA);
        assertEquals(mockEntity.getMaxHealth(), status.getMaxHealth(), DELTA);
        assertEquals(mockEntity.getDefense(), status.getDefense(), DELTA);
        assertEquals(mockEntity.getStrength(), status.getStrength(), DELTA);
    }

	
	@Nested
	class HealthManagement {
		
		@Test
		void testTakeDamage() {
			mockEntity.takeDamage(TEST_DAMAGE);
			double healthAfterDamage = mockEntity.getHealth();
			assertEquals(INITIAL_MAX_HEALTH - TEST_DAMAGE, healthAfterDamage);
		}
		
	    @Test
	    void testIsAlive() {
	        assertTrue(mockEntity.isAlive());
	        
	        mockEntity.takeDamage(200.0);
	        assertFalse(mockEntity.isAlive());
	    }

	    @Test
	    void testHealAndRestore() {
	    	mockEntity.takeDamage(50.0);
	    	mockEntity.heal(20.0);
	        assertEquals(INITIAL_MAX_HEALTH - 50 + 20 , mockEntity.getHealth(), DELTA);

	        mockEntity.restoreFullHP();
	        assertEquals(INITIAL_MAX_HEALTH, mockEntity.getHealth(), DELTA);
	    }
	    
	    @Test
	    void testMaxHealthModification() {
	    	mockEntity.increaseMaxHealth(20.0);
	        assertEquals(120.0, mockEntity.getMaxHealth(), DELTA);

	        mockEntity.decreaseMaxHealth(10.0);
	        assertEquals(110.0, mockEntity.getMaxHealth(), DELTA);
	    }
	}
    
	@Nested
	class DefenseAndStrength {
		
	    @Test
	    void testDefenseModification_increase() {
	    	mockEntity.increaseDefense(TEST_INCREASE_DEFENSE);
	        assertEquals(INITIAL_DEFENSE + TEST_INCREASE_DEFENSE, mockEntity.getDefense(), DELTA);
	    }

	    @Test
	    void testDefenseModification_increase_then_decrease() {
	    	mockEntity.increaseDefense(TEST_INCREASE_DEFENSE);
	        assertEquals(INITIAL_DEFENSE + TEST_INCREASE_DEFENSE, mockEntity.getDefense(), DELTA);

	        mockEntity.decreaseDefense(TEST_DECREASE_DEFENSE);
	        assertEquals(INITIAL_DEFENSE + TEST_INCREASE_DEFENSE - TEST_DECREASE_DEFENSE, mockEntity.getDefense(), DELTA);
	    }
	    
	}

    @Nested
    class Buffs {
    	
        @Test
        void testBuffs() {
            mockEntity.increaseStrength(TEST_INCREASE_STRENGTH);
            assertEquals(INITIAL_STRENGTH + TEST_INCREASE_STRENGTH, mockEntity.getStrength(), DELTA);
        }
        
    }
    
    @Nested
    class Effects {

    	@Test
    	void testEffects() {
    		TestEffect effect1 = new TestEffect(2);
    		TestEffect effect2 = new TestEffect(3);
    		
    		// Test adding normal effect
    		mockEntity.addEffect(effect1);
    		assertEquals(1, mockEntity.getEffects().size());
    		
    		// Test adding permanent effect
    		mockEntity.addPermanentEffectInRounds(effect2);
    		assertTrue(mockEntity.hasPermanentEffect(effect2));
    		
    		// Test applying effects
    		mockEntity.applyEffects();
    		assertEquals(1, effect1.getApplyCalls());
    	}
    	
    }
   
    // Helper classes for testing
    private static class TestEffect extends EffectInTurns {
        private int applyCalls = 0;

        public TestEffect(int duration) {
            super("TestEffect", duration);
        }

        @Override
        public void apply(Entity entity) {
            applyCalls++;
        }

        @Override
        public void remove(Entity entity) {}

        public int getApplyCalls() {
            return applyCalls;
        }

		@Override
		public void stack(EffectInTurns other) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getFormattedEffectInfo() {
			// TODO Auto-generated method stub
			return null;
		}
    }

    private static class TestCard implements ICard {
        @Override
        public String getName() {
            return "TestCard";
        }

        @Override
        public String getDescription() {
            return "Test Description";
        }
        
        @Override
        public ICard clone() {
        	return this;
        }
    }
}