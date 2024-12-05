package testings.card.defend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import card.defend.AdvancedDefendCard;
import card.defend.BasicDefendCard;
import card.defend.DefendCard;

class DefendCardTest {

    @Test
    void testBasicDefendCard() {
        BasicDefendCard basicCard = new BasicDefendCard();
        assertEquals(5, basicCard.getBlock());
        assertEquals("Basic Defend Card", basicCard.getName());
        assertEquals("Gain 5 block.", basicCard.getDescription());
    }

    @Test
    void testAdvancedDefendCard() {
        AdvancedDefendCard advancedCard = new AdvancedDefendCard();
        assertEquals(8, advancedCard.getBlock());
        assertEquals("Advance Defend Card", advancedCard.getName());
        assertEquals("Gain 8 block.", advancedCard.getDescription());
    }
    
    @Test
    void testCardCloning() {
        BasicDefendCard originalBasic = new BasicDefendCard();
        DefendCard clonedBasic = originalBasic.clone();
        
        assertNotNull(clonedBasic);
        assertNotSame(originalBasic, clonedBasic);
        assertEquals(originalBasic.getBlock(), clonedBasic.getBlock());
        assertEquals(originalBasic.getName(), clonedBasic.getName());
        assertEquals(originalBasic.getDescription(), clonedBasic.getDescription());
        
        AdvancedDefendCard originalAdvanced = new AdvancedDefendCard();
        DefendCard clonedAdvanced = originalAdvanced.clone();
        
        assertNotNull(clonedAdvanced);
        assertNotSame(originalAdvanced, clonedAdvanced);
        assertEquals(originalAdvanced.getBlock(), clonedAdvanced.getBlock());
        assertEquals(originalAdvanced.getName(), clonedAdvanced.getName());
        assertEquals(originalAdvanced.getDescription(), clonedAdvanced.getDescription());
    }
    
    @Test
    void testInheritance() {
        BasicDefendCard basicCard = new BasicDefendCard();
        AdvancedDefendCard advancedCard = new AdvancedDefendCard();
        
        assertTrue(basicCard instanceof DefendCard);
        assertTrue(advancedCard instanceof DefendCard);
    }
}