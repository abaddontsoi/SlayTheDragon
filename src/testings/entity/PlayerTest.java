package testings.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Player;

class PlayerTest {
	Player player;
	
	@BeforeEach
	void setUp() throws Exception {
		player = new Player(0, 0, 0, null);
	}

	@Test
	void testGetName() {
		String result = player.getName();
		assertEquals("Player", result);
	}

	@Test
	void testTakeDamage() {
		fail("Not yet implemented");
	}

	@Test
	void testRestoreFullHP() {
		fail("Not yet implemented");
	}

	@Test
	void testIncreaseStrength() {
		fail("Not yet implemented");
	}

	@Test
	void testHeal() {
		fail("Not yet implemented");
	}

	@Test
	void testIncreaseDefense() {
		fail("Not yet implemented");
	}

	@Test
	void testDecreaseDefense() {
		fail("Not yet implemented");
	}

	@Test
	void testIncreaseMaxHealth() {
		fail("Not yet implemented");
	}

	@Test
	void testDecreaseMaxHealth() {
		fail("Not yet implemented");
	}

	@Test
	void testAttackbuff() {
		fail("Not yet implemented");
	}

	@Test
	void testDefensebuff() {
		fail("Not yet implemented");
	}

	@Test
	void testIsAlive() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHealth() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMaxHealth() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDefense() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStrength() {
		fail("Not yet implemented");
	}

	@Test
	void testAddEffect() {
		fail("Not yet implemented");
	}

	@Test
	void testAddPermanentEffectInRounds() {
		fail("Not yet implemented");
	}

	@Test
	void testApplyEffects() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEffects() {
		fail("Not yet implemented");
	}

	@Test
	void testHasPermanentEffect() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEntityStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDeck() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCardToDeck() {
		fail("Not yet implemented");
	}

}
