package testings.battle;

import battle.FoeData;
import entity.Foe;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoeDataTest {

    private Foe foe;
    private FoeData foeData;

    @BeforeEach
    void setUp() {
        // Create a foe entity
        foe = new Foe("Goblin", "Elite", 50, 0, 0, new ArrayList<>()); // Name, Type, Health, Strength, Defense, Deck

        // Initialize FoeData with the foe
        foeData = new FoeData(foe);
    }

    @Test
    void testInitialValues() {
        // Verify initial values from FoeData
        assertEquals("Goblin", foeData.getEntityName(), "Foe name should be 'Goblin'.");
        assertEquals(50, foeData.getHealth(), "Foe's initial health should be 50.");

    }

    @Test
    void testAddPoison() {
        // Add poison to the foe
        foeData.addPoison(5);

        // Verify poison values
        assertEquals(5, foeData.getPoison(), "Foe should have 5 poison stacks.");
        assertEquals(5, foeData.getReceivedPoison(), "Foe should have received 5 poison stacks in total.");
    }

    @Test
    void testReducePoison() {
        // Add and reduce poison
        foeData.addPoison(3);
        foeData.reducePoison();

        // Verify the reduced poison
        assertEquals(2, foeData.getPoison(), "Foe should have 2 poison stacks after reducing.");
    }

    @Test
    void testAddAttackDamage() {
        // Add attack damage
        foeData.addAttackDamage(20);

        // Verify attack damage
        assertEquals(20, foeData.getAttackDamage(), "Foe's attack damage should be 20.");
    }

    @Test
    void testSetAttackDamage() {
        // Set attack damage directly
        foeData.setAttackDamage(15);

        // Verify attack damage
        assertEquals(15, foeData.getAttackDamage(), "Foe's attack damage should be 15.");
    }

    @Test
    void testAddDefense() {
        // Add defense
        foeData.addDefense(10);

        // Verify defense value
        assertEquals(10, foeData.getDefense(), "Foe's defense should be 10.");
    }

    @Test
    void testSetDefense() {
        // Set defense directly
        foeData.setDefense(8);

        // Verify defense value
        assertEquals(8, foeData.getDefense(), "Foe's defense should be 8.");
    }

    @Test
    void testTakeDamage() {
        // Foe takes damage
        foeData.takeDamage(20);

        // Verify health and received damage
        assertEquals(30, foeData.getHealth(), "Foe's health should decrease to 30 after taking 20 damage.");
        assertEquals(20, foeData.getReceivedDamage(), "Foe's total received damage should be 20.");
    }

    @Test
    void testAddTotalCardsPlayed() {
        // Add cards played
        foeData.addTotalCardsPlayed();
        foeData.addTotalCardsPlayed();

        // Verify total cards played
        assertEquals(2, foeData.getTotalCardsPlayed(), "Foe should have played 2 cards.");
    }

    @Test
    void testAddTotalHeal() {
        // Add healing
        foeData.addTotalHeal(10);

        // Verify total healing
        assertEquals(10, foeData.getTotalHeal(), "Foe's total healing should be 10.");
    }

    @Test
    void testResetRoundData() {
        // Modify round data
        foeData.setAttackDamage(10);
        foeData.setAttackBuff(1.5);
        foeData.setDefense(20);
        foeData.setDefenseBuff(1.2);

        // Reset round data
        foeData.resetRoundData();

        // Verify round data is reset
        assertEquals(0, foeData.getAttackDamage(), "Foe's attack damage should be reset to 0.");
        assertEquals(1.0, foeData.getAttackBuff(), "Foe's attack buff should be reset to 1.0.");
        assertEquals(0, foeData.getDefense(), "Foe's defense should be reset to 0.");
        assertEquals(1.0, foeData.getDefenseBuff(), "Foe's defense buff should be reset to 1.0.");
    }

    @Test
    void testResetAllData() {
        // Modify various data
        foeData.setAttackDamage(10);
        foeData.setAttackBuff(1.5);
        foeData.setDefense(20);
        foeData.setDefenseBuff(1.2);
        foeData.addPoison(5);
        foeData.addTotalHeal(10);
        foeData.takeDamage(20);
        foeData.addTotalCardsPlayed();

        // Reset all data
        foeData.reset();

        // Verify all data is reset
        assertEquals(0, foeData.getAttackDamage(), "Foe's attack damage should be reset to 0.");
        assertEquals(0, foeData.getDefense(), "Foe's defense should be reset to 0.");
        assertEquals(0, foeData.getPoison(), "Foe's poison should be reset to 0.");
        assertEquals(0, foeData.getTotalHeal(), "Foe's total heal should be reset to 0.");
        assertEquals(0, foeData.getReceivedDamage(), "Foe's received damage should be reset to 0.");
        assertEquals(0, foeData.getTotalCardsPlayed(), "Foe's total cards played should be reset to 0.");
        assertEquals(0, foeData.getReceivedPoison(), "Foe's received poison should be reset to 0.");
        assertEquals(1.0, foeData.getAttackBuff(), "Foe's attack buff should be reset to 1.0.");
        assertEquals(1.0, foeData.getDefenseBuff(), "Foe's defense buff should be reset to 1.0.");
    }

    @Test
    void testRoundsPlayed() {
        // Increment rounds played
        foeData.doneRound();
        foeData.doneRound();

        // Verify number of rounds
        assertEquals(2, foeData.getRounds(), "Foe should have completed 2 rounds.");
    }
}