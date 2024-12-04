package testings.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import card.*;
import card.attack.*;
import card.defend.*;
import card.skill.*;

class CardFactoryTest {
    
    private CardFactory cardFactory;
    
    @BeforeEach
    void setUp() {
        cardFactory = CardFactory.getInstance();
    }
    
    @Nested
    class SingletonTests {
        @Test
        void testSingletonInstance() {
            CardFactory instance1 = CardFactory.getInstance();
            CardFactory instance2 = CardFactory.getInstance();
            assertSame(instance1, instance2);
        }
    }
    
    @Nested
    class CardCreationTests {
        
        @Test
        void testCreateBasicAttackCard() {
            ICard card = cardFactory.createCard(CardName.ATTACK_BASIC);
            assertNotNull(card);
            assertTrue(card instanceof BasicAttackCard);
        }
        
        @Test
        void testCreateBasicDefendCard() {
            ICard card = cardFactory.createCard(CardName.DEFEND_BASIC);
            assertNotNull(card);
            assertTrue(card instanceof BasicDefendCard);
        }
        
        @Test
        void testCreateAdvancedAttackCard() {
            ICard card = cardFactory.createCard(CardName.ATTACK_ADVANCED);
            assertNotNull(card);
            assertTrue(card instanceof AdvancedAttackCard);
        }
        
        @Test
        void testCreateAdvancedDefendCard() {
            ICard card = cardFactory.createCard(CardName.DEFEND_ADVANCED);
            assertNotNull(card);
            assertTrue(card instanceof AdvancedDefendCard);
        }
        
        @ParameterizedTest
        @EnumSource(CardName.class)
        void testCreateAllCards(CardName cardName) {
            ICard card = cardFactory.createCard(cardName);
            assertNotNull(card);
        }
        
    }
    
    @Nested
    class ErrorHandlingTests {
        @Test
        void testCreateCardWithNullCardName() {
            assertThrows(IllegalArgumentException.class, 
                () -> cardFactory.createCard(null)
            );
        }
        
        @Test
        void testCardCreationConsistency() {
            CardName testCardName = CardName.ATTACK_BASIC;
            ICard card1 = cardFactory.createCard(testCardName);
            ICard card2 = cardFactory.createCard(testCardName);
            
            assertAll(
                () -> assertNotNull(card1),
                () -> assertNotNull(card2),
                () -> assertNotSame(card1, card2),
                () -> assertEquals(card1.getClass(), card2.getClass())
            );
        }
    }
    
    @Nested
    class CardPropertiesTests {
        @Test
        void testBasicAttackCardProperties() {
            ICard card = cardFactory.createCard(CardName.ATTACK_BASIC);
            assertAll(
                () -> assertNotNull(card.getName()),
                () -> assertNotNull(card.getDescription())
            );
        }
        
        @Test
        void testBasicDefendCardProperties() {
            ICard card = cardFactory.createCard(CardName.DEFEND_BASIC);
            assertAll(
                () -> assertNotNull(card.getName()),
                () -> assertNotNull(card.getDescription())

            );
        }
    }
    
    @Nested
    class CardTypeTests {
        @Test
        void testAttackCards() {
            assertAll(
                () -> assertTrue(cardFactory.createCard(CardName.ATTACK_BASIC) instanceof AttackCard),
                () -> assertTrue(cardFactory.createCard(CardName.ATTACK_ADVANCED) instanceof AttackCard)
            );
        }
        
        @Test
        void testDefendCards() {
            assertAll(
                () -> assertTrue(cardFactory.createCard(CardName.DEFEND_BASIC) instanceof DefendCard),
                () -> assertTrue(cardFactory.createCard(CardName.DEFEND_ADVANCED) instanceof DefendCard)
            );
        }
        
        @Test
        void testSkillCards() {
            assertAll(
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_HEAL) instanceof SkillCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_HEAL) instanceof SkillCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_POISON) instanceof SkillCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_POISON) instanceof SkillCard)
            );
        }
        
        @Test
        void testCreateSkillCards() {
            assertAll(
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_HEAL) instanceof BasicHealCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_HEAL) instanceof AdvancedHealCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_POISON) instanceof BasicPoisonCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_POISON) instanceof AdvancedPoisonCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_DRAW) instanceof BasicDrawCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_DRAW) instanceof AdvancedDrawCard),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_ATTACK_BUFF) instanceof BasicAttackBuff),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_ATTACK_BUFF) instanceof AdvancedAttackBuff),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_BASIC_DEFENSE_BUFF) instanceof BasicDefenseBuff),
                () -> assertTrue(cardFactory.createCard(CardName.SKILL_ADVANCED_DEFENSE_BUFF) instanceof AdvancedDefenseBuff)
            );
        }

    }
}