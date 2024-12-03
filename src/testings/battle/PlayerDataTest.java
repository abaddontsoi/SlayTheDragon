package testings.battle;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import battle.PlayerData;
//import card.ICard;
//import entity.Player;
//
//class PlayerDataTest {
//
//	private PlayerData playerData;
//	private Player player;
//	
//	private static final double INITIAL_MAX_HEALTH = 100;
//	private static final int INITIAL_DEFENSE = 10;
//	private static final int INITIAL_STRENGTH = 20;
//	private static final List<ICard> INITIAL_DECK = new ArrayList<ICard>();
//
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		player = new Player(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
//		playerData = new PlayerData(player);
//	}	
//}


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import battle.PlayerData;
import card.ICard;
import entity.Player;

import java.util.ArrayList;
import java.util.List;

class PlayerDataTest {
    private PlayerData playerData;
    private Player testPlayer;
   	
	private static final double INITIAL_MAX_HEALTH = 100;
	private static final int INITIAL_DEFENSE = 10;
	private static final int INITIAL_STRENGTH = 20;
	private static final List<ICard> INITIAL_DECK = new ArrayList<ICard>();

	
    // Test implementation of ICard
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

    @BeforeEach
    void setUp() {
        testPlayer = new Player(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
        playerData = new PlayerData(testPlayer);
    }

    @Test
    void testConstructor() {
        assertEquals(0, playerData.getMaxDamage());
        assertEquals(0, playerData.getTotalAttackDamage());
        assertEquals(0, playerData.getTotalDefense());
        assertTrue(playerData.getRewards().isEmpty());
    }

    @Test
    void testRewardSystem() {
        // Test adding single reward
        ICard card1 = new TestCard();
        playerData.addReward(card1);
        assertEquals(1, playerData.getRewards().size());
        assertSame(card1, playerData.getRewards().get(0));

        // Test adding multiple rewards
        ICard card2 = new TestCard();
        ICard card3 = new TestCard();
        playerData.addReward(card2);
        playerData.addReward(card3);
        assertEquals(3, playerData.getRewards().size());
        
        // Verify rewards list maintains order
        List<ICard> rewards = playerData.getRewards();
        assertSame(card1, rewards.get(0));
        assertSame(card2, rewards.get(1));
        assertSame(card3, rewards.get(2));
    }

    @Test
    void testTotalAttackDamage() {
        // Test initial value
        assertEquals(0, playerData.getTotalAttackDamage());

        // Test adding single value
        playerData.addTotalAttackDamage(10);
        assertEquals(10, playerData.getTotalAttackDamage());

        // Test accumulating values
        playerData.addTotalAttackDamage(15);
        assertEquals(25, playerData.getTotalAttackDamage());

        // Test adding negative value
        playerData.addTotalAttackDamage(-5);
        assertEquals(20, playerData.getTotalAttackDamage());

        // Test adding zero
        playerData.addTotalAttackDamage(0);
        assertEquals(20, playerData.getTotalAttackDamage());
    }

    @Test
    void testTotalDefense() {
        // Test initial value
        assertEquals(0, playerData.getTotalDefense());

        // Test adding single value
        playerData.addTotalDefense(10);
        assertEquals(10, playerData.getTotalDefense());

        // Test accumulating values
        playerData.addTotalDefense(15);
        assertEquals(25, playerData.getTotalDefense());

        // Test adding negative value
        playerData.addTotalDefense(-5);
        assertEquals(20, playerData.getTotalDefense());

        // Test adding zero
        playerData.addTotalDefense(0);
        assertEquals(20, playerData.getTotalDefense());
    }

    @Test
    void testMaxDamage() {
        // Test initial value
        assertEquals(0, playerData.getMaxDamage());

        // Test updating with larger value
        playerData.updateMaxDamage(10);
        assertEquals(10, playerData.getMaxDamage());

        // Test updating with smaller value (should not change)
        playerData.updateMaxDamage(5);
        assertEquals(10, playerData.getMaxDamage());

        // Test updating with same value
        playerData.updateMaxDamage(10);
        assertEquals(10, playerData.getMaxDamage());

        // Test updating with larger value again
        playerData.updateMaxDamage(20);
        assertEquals(20, playerData.getMaxDamage());

        // Test updating with negative value
        playerData.updateMaxDamage(-5);
        assertEquals(20, playerData.getMaxDamage());
    }

    @Test
    void testMaxDefense() {
        // Test initial value
        assertEquals(0, playerData.getMaxDamage());

        // Test updating with larger value
        playerData.updateMaxDefense(10);
        assertEquals(10, playerData.getMaxDefense());

        // Test updating with smaller value (should not change)
        playerData.updateMaxDefense(5);
        assertEquals(10, playerData.getMaxDefense());

        // Test updating with same value
        playerData.updateMaxDefense(10);
        assertEquals(10, playerData.getMaxDefense());

        // Test updating with larger value again
        playerData.updateMaxDefense(20);
        assertEquals(20, playerData.getMaxDefense());

        // Test updating with negative value
        playerData.updateMaxDefense(-5);
        assertEquals(20, playerData.getMaxDefense());
    }

    @Test
    void testInheritedMethods() {
        // Test that inherited methods from EntityData work correctly
        assertEquals("Player", playerData.getEntityName());
        assertEquals(INITIAL_MAX_HEALTH, playerData.getHealth());
        assertEquals(INITIAL_DEFENSE, playerData.getBasicDefense());
        assertEquals(INITIAL_STRENGTH, playerData.getBasicStrength());
    }

    @Test
    void testEdgeCases() {
        // Test adding null reward
        assertDoesNotThrow(() -> playerData.addReward(null));
        assertEquals(1, playerData.getRewards().size());
        assertNull(playerData.getRewards().get(0));

        // Test Integer.MAX_VALUE cases
        playerData.addTotalAttackDamage(Integer.MAX_VALUE);
        playerData.addTotalAttackDamage(1);
        assertEquals(Integer.MAX_VALUE, (long) playerData.getTotalAttackDamage());

        playerData.addTotalDefense(Integer.MAX_VALUE);
        playerData.addTotalDefense(1);
        assertEquals(Integer.MAX_VALUE, (long) playerData.getTotalDefense());
    }
}