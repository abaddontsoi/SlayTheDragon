package testings.card.attack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import card.CardFactory;
import card.CardName;
import card.attack.AdvancedAttackCard;
import card.attack.AttackCard;
import card.attack.BasicAttackCard;

class AttackCardTest {
	
	private final static String INITIAL_TEST_ATTACK_CARD_NAME = "TestAttackCard";
	private final static String INITIAL_TEST_ATTACK_CARD_INFO = "TestAttackCard info";
	private final static int INITIAL_TEST_ATTACK_CARD_DAMAGE = 20;
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void attackCardCloning() {
		TestAttackCard card = new TestAttackCard(INITIAL_TEST_ATTACK_CARD_DAMAGE);
		assertDoesNotThrow(() -> {
			AttackCard temp = card.clone();
			assertNotNull(temp);
			assertEquals(INITIAL_TEST_ATTACK_CARD_NAME, temp.getName());
			assertEquals(INITIAL_TEST_ATTACK_CARD_INFO, temp.getDescription());
			assertEquals(INITIAL_TEST_ATTACK_CARD_DAMAGE, temp.getDamage());
			
		});
	}

	@Nested
	class AllAttackCards {
		CardFactory factory; 
		@BeforeEach
		void setUp() throws Exception {
			factory = CardFactory.getInstance();
		}
		
        @Test
        void testCreateAttackCards() {
            assertAll(
                () -> assertTrue(factory.createCard(CardName.ATTACK_BASIC) instanceof AttackCard),
                () -> assertTrue(factory.createCard(CardName.ATTACK_ADVANCED) instanceof AttackCard)
            );
        }
        
        @Test
        void testBasicAttackCard() {
        	AttackCard card = new BasicAttackCard();
        	assertEquals(5, card.getDamage());
        	assertEquals("Basic Attack Card", card.getName());
        	assertEquals("Deal 5 damage.", card.getDescription());
        }
        
        @Test
        void testAdvanceAttackCard() {
        	AttackCard card = new AdvancedAttackCard();
        	assertEquals(10, card.getDamage());
        	assertEquals("Advance Attack Card", card.getName());
        	assertEquals("Deal 10 damage.", card.getDescription());

        }
	}
	
	private class TestAttackCard extends AttackCard {

		public TestAttackCard(int damage) {
			super(damage);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return INITIAL_TEST_ATTACK_CARD_NAME;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return INITIAL_TEST_ATTACK_CARD_INFO;
		}
		
	}

}
