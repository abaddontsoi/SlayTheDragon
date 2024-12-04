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
	
	@BeforeEach
	void setUp() throws Exception {
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
}
