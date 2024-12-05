package testings.card.skill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import card.skill.*;

class SkillCardTest {

    @Test
    void testBasicHealCard() {
        BasicHealCard basicHeal = new BasicHealCard();
        assertEquals("Basic Heal Card", basicHeal.getName());
        assertEquals("Heal 5 HP.", basicHeal.getDescription());
    }

    @Test
    void testAdvancedHealCard() {
        AdvancedHealCard advancedHeal = new AdvancedHealCard();
        assertEquals("Advance Heal Card", advancedHeal.getName());
        assertEquals("Heal 10 HP.", advancedHeal.getDescription());
    }

    @Test
    void testBasicDrawCard() {
        BasicDrawCard basicDraw = new BasicDrawCard();
        assertEquals("Basic Draw Card", basicDraw.getName());
        assertEquals("Drawn 2 cards.", basicDraw.getDescription());
    }

    @Test
    void testAdvancedDrawCard() {
        AdvancedDrawCard advancedDraw = new AdvancedDrawCard();
        assertEquals("Advanced Draw Card", advancedDraw.getName());
        assertEquals("Drawn 3 cards.", advancedDraw.getDescription());
    }

    @Test
    void testBasicDefenseBuff() {
        BasicDefenseBuff basicDefBuff = new BasicDefenseBuff();
        assertEquals(1.5, basicDefBuff.getdefbuff());
        assertEquals("Basic Defense Buff Card", basicDefBuff.getName());
        assertEquals("Defense buffed 1.5 times.", basicDefBuff.getDescription());
    }

    @Test
    void testAdvancedDefenseBuff() {
        AdvancedDefenseBuff advancedDefBuff = new AdvancedDefenseBuff();
        assertEquals(2.0, advancedDefBuff.getdefbuff());
        assertEquals("Advanced Defense Buff Card", advancedDefBuff.getName());
        assertEquals("Defense buffed 2.0 times.", advancedDefBuff.getDescription());
    }

    @Test
    void testBasicAttackBuff() {
        BasicAttackBuff basicAtkBuff = new BasicAttackBuff();
        assertEquals(1.5, basicAtkBuff.getatkbuff());
        assertEquals("Basic Attack Buff", basicAtkBuff.getName());
        assertEquals("Attack buffed 1.5 times.", basicAtkBuff.getDescription());
    }

    @Test
    void testAdvancedAttackBuff() {
        AdvancedAttackBuff advancedAtkBuff = new AdvancedAttackBuff();
        assertEquals(2.0, advancedAtkBuff.getatkbuff());
        assertEquals("Advanced Attack Buff", advancedAtkBuff.getName());
        assertEquals("Attack buffed 2.0 times.", advancedAtkBuff.getDescription());
    }

    @Test
    void testAdvancedPoisonCard() {
        AdvancedPoisonCard advancedPoison = new AdvancedPoisonCard();
        assertEquals(10, advancedPoison.getDamage());
        assertEquals("Advance Poison Card", advancedPoison.getName());
        assertEquals("Deal 10 damage.", advancedPoison.getDescription());
    }

    @Test
    void testInheritance() {
        assertTrue(new BasicHealCard() instanceof SkillCard);
        assertTrue(new AdvancedHealCard() instanceof SkillCard);
        assertTrue(new BasicDrawCard() instanceof SkillCard);
        assertTrue(new AdvancedDrawCard() instanceof SkillCard);
        assertTrue(new BasicDefenseBuff() instanceof SkillCard);
        assertTrue(new AdvancedDefenseBuff() instanceof SkillCard);
        assertTrue(new BasicAttackBuff() instanceof SkillCard);
        assertTrue(new AdvancedAttackBuff() instanceof SkillCard);
        assertTrue(new AdvancedPoisonCard() instanceof SkillCard);
    }
}