package testings.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.ICard;
import entity.Player;

class PlayerTest {
	private Player player;
	private static List<ICard> INITIAL_DECK;
	
	private static final double INITIAL_MAX_HEALTH = 120;
	private static final int INITIAL_DEFENSE = 10;
	private static final int INITIAL_STRENGTH = 20;
	private static final double DELTA = 0.0001;
	
	@BeforeEach
	void setUp() throws Exception {
		INITIAL_DECK = new ArrayList<>();
		player = new Player(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
	}
	
	@Test
	void testConstructor() {
        assertEquals(INITIAL_MAX_HEALTH, player.getMaxHealth(), DELTA);
        assertEquals(INITIAL_MAX_HEALTH, player.getHealth(), DELTA);
        assertEquals(INITIAL_DEFENSE, player.getDefense(), DELTA);
        assertEquals(INITIAL_STRENGTH, player.getStrength(), DELTA);
        assertTrue(player.getEffects().isEmpty());
        assertEquals("Player", player.getName());
	}
	
	@Test
	void testGetName() {
		String result = player.getName();
		assertEquals("Player", result);
	}
}
