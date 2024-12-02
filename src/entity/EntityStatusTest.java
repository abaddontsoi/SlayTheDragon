package entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import effect.EffectInTurns;

class EntityStatusTest {
	EntityStatus status;
	
	int setMaxHealthTestValue = 100;
	int setHealthTestValue = 90;
	int setDefenseTestValue = 10;
	int setStrengthTestValue = 8;
	List<EffectInTurns> setEffectsInRoundsTestValue = null;
	List<EffectInTurns> setPermanentEffectsInRoundsTestValue = null;
	double takeDamageTestValue = 10;
	double healTestValue = 9;
	double increaseDefenseTestValue = 11; 
	double increaseStrengthTestValue = 4;
	double increaseMaxHealthTestValue = 7;
	double attackbuffTestValue = 2.0;
	double defensebuffTestValue = 3.0;

	
	@BeforeEach
	void setUp() throws Exception {
		status = new EntityStatus(0, 0, 0);
	}

	@Test
	void testEntityStatus() {
		assertNotNull(status);
	}
	
	@Test
	void testSetMaxHealth() {
		status.setMaxHealth(setMaxHealthTestValue);
	}

	@Test
	void testSetHealth() {
		status.setHealth(setHealthTestValue);
	}

	@Test
	void testSetDefense() {
		status.setDefense(setDefenseTestValue);
	}

	@Test
	void testSetStrength() {
		status.setStrength(setStrengthTestValue);
	}

	@Test
	void testSetEffectsInRounds() {
		status.setEffectsInRounds(setEffectsInRoundsTestValue);
	}

	@Test
	void testSetPermanentEffectsInRounds() {
		status.setPermanentEffectsInRounds(setPermanentEffectsInRoundsTestValue);
	}

	@Test
	void testTakeDamage() {
		status.takeDamage(takeDamageTestValue);
	}

	@Test
	void testHeal_01() {
		EntityStatus es = new EntityStatus(100, 0, 0);
		es.heal(healTestValue);
	}
	
	@Test
	void testHeal_02() {
		EntityStatus es = new EntityStatus(100, 0, 0);
		es.setHealth(40);
		es.heal(healTestValue);
		double health = es.getHealth();
		assertEquals(40 + healTestValue, health);
	}

	@Test
	void testIncreaseDefense() {
		status.increaseDefense(increaseDefenseTestValue);
	}

	@Test
	void testIncreaseStrength() {
		status.increaseStrength(increaseStrengthTestValue);
	}

	@Test
	void testIncreaseMaxHealth() {
		status.increaseMaxHealth(increaseMaxHealthTestValue);
	}

	@Test
	void testAttackbuff() {
		status.attackbuff(attackbuffTestValue);
	}

	@Test
	void testDefensebuff() {
		status.defensebuff(defensebuffTestValue);
	}

	@Test
	void testAddEffect() {
		status.addEffect(null);
	}

//	@Test
//	void testAddPermanentEffectInRounds() {
//		status.addPermanentEffectInRounds(null, null);
//	}

	@Test
	void testApplyEffects() {
		status.applyEffects(null, null);
	}

	@Test
	void testIsAlive_01() {
		EntityStatus es = new EntityStatus(10, 0, 0);
		es.setHealth(1);
		boolean result = es.isAlive();
		assertEquals(true, result);
	}
	
	@Test
	void testIsAlive_02() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		boolean result = es.isAlive();
		assertEquals(false, result);
	}
	
	@Test
	void testGetStatusCopy() {
		EntityStatus copy = status.getStatusCopy();
		assertNotNull(copy);
	}
	
	@Test
	void testGetMaxHealth() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		es.setMaxHealth(setMaxHealthTestValue);
		double result = es.getMaxHealth();
		assertEquals(setMaxHealthTestValue, result);
	}

	@Test
	void testGetHealth() {
		EntityStatus es = new EntityStatus(100, 0, 0);
		es.setHealth(setHealthTestValue);
		double result = es.getHealth();
		assertEquals(setHealthTestValue, result);
	}

	@Test
	void testGetDefense() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		es.setDefense(setDefenseTestValue);
		double result = es.getDefense();
		assertEquals(setDefenseTestValue, result);
	}

	@Test
	void testGetStrength() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		es.setStrength(setStrengthTestValue);
		int result = es.getStrength();
		assertEquals(setStrengthTestValue, result);
	}

	@Test
	void testGetEffectsInRounds() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		List<EffectInTurns> result = es.getEffectsInRounds();
		assertNotNull(result);
	}

	@Test
	void testGetPermanentEffectsInRounds() {
		EntityStatus es = new EntityStatus(0, 0, 0);
		List<EffectInTurns> result = es.getPermanentEffectsInRounds();
		assertNotNull(result);
	}


}
