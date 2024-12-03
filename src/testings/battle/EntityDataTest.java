package testings.battle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import battle.EntityData;
import card.ICard;
import entity.Entity;

class EntityDataTest {

	EntityDataStub entityData;
	EntityStub entity;
	
	private static final String INITIAL_ENTITY_NAME = "Entity";
	private static final double INITIAL_MAX_HEALTH = 100;
	private static final int INITIAL_DEFENSE = 10;
	private static final int INITIAL_STRENGTH = 20;
	private static final List<ICard> INITIAL_DECK = new ArrayList<ICard>();

	private static final int TEST_SET_DEFENSE = 15;
	private static final int TEST_ADD_DEFENSE = 5;
	private static final int TEST_SET_ATTACK_DAMAGE = 20;
	private static final int TEST_ADD_ATTACK_DAMAGE = 30;
	
	private static final int TEST_SET_ATTACK_BUFF = 20;
	private static final int TEST_SET_DEFENSE_BUFF = 30;
	
	private static final int TEST_ADD_POISON = 10;
	private static final int TEST_OCCURRENCE_OF_REDUCE = 5;
	
	private static final int TEST_TAKE_DAMAGE_INT = 6;
	private static final double TEST_TAKE_DAMAGE_DOUBLE = 7.5;
	private static final int TEST_ADD_RECEIVED_DAMAGE = 12;
	private static final double TEST_TAKE_DAMAGE_TO_DEATH = 2 * INITIAL_MAX_HEALTH;
	
	private static final int TEST_ADD_HEAL = 9;
	
	private static final int TEST_OCCURRENCE_OF_DONE_ROUND = 7;
	private static final int TEST_OCCURRENCE_OF_PLAY_CARD = 31;

	
	@BeforeEach
	void setUp() throws Exception {
		entity = new EntityStub(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
		entityData = new EntityDataStub(entity);
	}

	@Nested
	class StrengthAndDefense {
		
		@Test
		void testDefense() {
			entityData.setDefense(TEST_SET_DEFENSE);
			entityData.addDefense(TEST_ADD_DEFENSE);
			
			int result = entityData.getDefense();
			assertEquals(TEST_SET_DEFENSE + TEST_ADD_DEFENSE, result);
			assertEquals(INITIAL_DEFENSE, entityData.getBasicDefense());
		}
		
		@Test
		void testStrengthAndAttack() {
			entityData.setAttackDamage(TEST_SET_ATTACK_DAMAGE);
			entityData.addAttackDamage(TEST_ADD_ATTACK_DAMAGE);
			
			int result = entityData.getAttackDamage();
			assertEquals(TEST_SET_ATTACK_DAMAGE + TEST_ADD_ATTACK_DAMAGE, result);
		}
	}
	
	
	@Nested
	class Buffs {
		
		@Test
		void testAttackBuff() {
			assertEquals(1, entityData.getAttackBuff());
			
			entityData.setAttackBuff(TEST_SET_ATTACK_BUFF);
			assertEquals(TEST_SET_ATTACK_BUFF, entityData.getAttackBuff());
			
			entityData.reset();
			assertEquals(1, entityData.getAttackBuff());
		}
		
		@Test
		void testDefenseBuff() {
			assertEquals(1, entityData.getDefenseBuff());
			
			entityData.setDefenseBuff(TEST_SET_DEFENSE_BUFF);
			assertEquals(TEST_SET_DEFENSE_BUFF, entityData.getDefenseBuff());
			
			entityData.reset();
			assertEquals(1, entityData.getDefenseBuff());
		}
	}
	
	@Nested
	class Poison {

		@Test
		void testPoison_added_first_time() {
			entityData.addPoison(TEST_ADD_POISON);
			
			int currentPoison = entityData.getPoison();
			int receivedPoison = entityData.getReceivedPoison();
			
			assertEquals(TEST_ADD_POISON, currentPoison);
			assertEquals(TEST_ADD_POISON, receivedPoison);
		}
		
		@Test
		void testPoison_add_and_reduce() {
			entityData.addPoison(TEST_ADD_POISON);

			for (int i = 0; i < TEST_OCCURRENCE_OF_REDUCE; i++) 
				entityData.reducePoison();
			
			int currentPoison = entityData.getPoison();
			int receivedPoison = entityData.getReceivedPoison();
			
			assertEquals(TEST_ADD_POISON - TEST_OCCURRENCE_OF_REDUCE, currentPoison);
			assertEquals(TEST_ADD_POISON, receivedPoison);
		}
	}
	
	@Nested
	class Damage {
		
		@Test
		void testAddReceivedDamage() {
			// No received damage at the beginning
			assertEquals(0, entityData.getReceivedDamage());

			entityData.addReceivedDamage(TEST_ADD_RECEIVED_DAMAGE);
			int result = entityData.getReceivedDamage();
			assertEquals(TEST_ADD_RECEIVED_DAMAGE, result);
		}
		
		@Test
		void testTakeDamage_double() {
			// No received damage at the beginning
			assertEquals(0, entityData.getReceivedDamage());
			
			entityData.takeDamage(TEST_TAKE_DAMAGE_DOUBLE);
			int result = entityData.getReceivedDamage();
			assertEquals((int) TEST_TAKE_DAMAGE_DOUBLE, result);
			
			assertEquals((int) (INITIAL_MAX_HEALTH - TEST_TAKE_DAMAGE_DOUBLE), entityData.getHealth());
		}

		@Test
		void testTakeDamage_int() {
			// No received damage at the beginning
			assertEquals(0, entityData.getReceivedDamage());
			
			entityData.takeDamage(TEST_TAKE_DAMAGE_INT);
			int result = entityData.getReceivedDamage();
			assertEquals(TEST_TAKE_DAMAGE_INT, result);
			
			assertEquals(INITIAL_MAX_HEALTH - TEST_TAKE_DAMAGE_INT, entityData.getHealth());
		}
		
		@Test
		void testTakeDamageToDeath() {
			// No received damage at the beginning
			assertEquals(0, entityData.getReceivedDamage());
			
			entityData.takeDamage(TEST_TAKE_DAMAGE_TO_DEATH);
			int result = entityData.getReceivedDamage();
			assertEquals(TEST_TAKE_DAMAGE_TO_DEATH, result);
			
			assertEquals(0, entityData.getHealth());
		}
	}
	 
	@Nested
	class Health {
		
		@Test
		void testAddHeal_after_add_heal() {
			assertEquals(0, entityData.getTotalHeal());
			
			entityData.addTotalHeal(TEST_ADD_HEAL);
			int result = entityData.getTotalHeal();
			assertEquals(TEST_ADD_HEAL, result);
		}
	}
	
	@Nested
	class RoundAndEntityInfo {
		@Test
		void testGetName() {
			String result = entityData.getEntityName();
			
			assertNotNull(result);
			assertEquals(INITIAL_ENTITY_NAME, result);
		}
		
		@Test
		void testGetEntity() {
			Entity result = entityData.getEntity();
			
			assertNotNull(result);
			assertEquals(entity, result);
		}
		
		@Test
		void testRounds() {
			// Round count should be 0 at the beginning
			assertEquals(0, entityData.getRounds());
			
			for (int i=0; i<TEST_OCCURRENCE_OF_DONE_ROUND; i++) 
				entityData.doneRound();
			
			assertEquals(TEST_OCCURRENCE_OF_DONE_ROUND, entityData.getRounds());
		}
		
		@Test
		void testPlayCards() {
			// Card count should be 0 at the beginning
			assertEquals(0, entityData.getTotalCardsPlayed());
			
			for (int i=0; i<TEST_OCCURRENCE_OF_PLAY_CARD; i++) 
				entityData.addTotalCardsPlayed();
			
			assertEquals(TEST_OCCURRENCE_OF_PLAY_CARD, entityData.getTotalCardsPlayed());

		}
		
		@Test
		void testRoundReset() {
			entityData.resetRoundData();
			
			assertEquals(0, entityData.getAttackDamage());
			assertEquals(1, entityData.getAttackBuff());
			assertEquals(0, entityData.getDefense());
			assertEquals(1, entityData.getDefenseBuff());
		}
	}
	
	@Test
    void testReset() {
        // Set some values
        entityData.addPoison(5);
        entityData.addAttackDamage(20);
        entityData.setAttackBuff(2.0);
        entityData.addDefense(30);
        entityData.setDefenseBuff(1.5);
        entityData.addTotalCardsPlayed();
        entityData.doneRound();

        // Reset
        entityData.reset();

        // Verify reset values
        assertEquals(0, entityData.getBasicDefense());
        assertEquals(0, entityData.getBasicStrength());
        assertEquals(0, entityData.getDefense());
        assertEquals(0, entityData.getAttackDamage());

        assertEquals(1.0, entityData.getDefenseBuff());
        assertEquals(1.0, entityData.getAttackBuff());

        assertEquals(0, entityData.getPoison());
        assertEquals(0, entityData.getReceivedPoison());

        assertEquals(0, entityData.getReceivedDamage());
        assertEquals(0, entityData.getTotalHeal());
        
        assertEquals(0, entityData.getTotalCardsPlayed());
        assertEquals(0, entityData.getRounds());
    }
	
	private class EntityDataStub extends EntityData {

		public EntityDataStub(Entity entity) {
			super(entity);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	private class EntityStub extends Entity {

		public EntityStub(double maxHealth, int defense, int strength, List<ICard> deck) {
			super(maxHealth, defense, strength, deck);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return INITIAL_ENTITY_NAME;
		}
		
	}
	
}
