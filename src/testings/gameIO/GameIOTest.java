package testings.gameIO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.ICard;
import card.attack.BasicAttackCard;
import entity.Foe;
import entity.Player;
import gameIO.GameIO;

class GameIOTest {

    private GameIO gameIO;

    @BeforeEach
    void setUp() {
        resetGameIOInstance();
        gameIO = GameIO.getInstance();
    }

    @Test
    void testGetInstance() {
        GameIO instance1 = GameIO.getInstance();
        GameIO instance2 = GameIO.getInstance();
        
        assertSame(instance1, instance2, "getInstance should return the same instance");
        assertNotNull(instance1, "getInstance should not return null");
    }

    @Test
    void testDisplayBattleStart() {
        Player player = new Player(100, 0, 0, null);
        Foe foe = new Foe(null, null, 0, 0, 0, null);
        
        // This will print to console, we're just verifying no exceptions are thrown
        assertDoesNotThrow(() -> gameIO.displayBattleStart(player, foe, 1));
    }

    @Test
    void testPromptCardSelection() {
        List<ICard> hand = new ArrayList<>();
        ICard testCard = new BasicAttackCard();
        hand.add(testCard);
        
        // Since we can't simulate user input easily without mocking,
        // we'll just verify the method executes without throwing exceptions
        assertDoesNotThrow(() -> gameIO.promptCardSelection(hand));
    }

    @Test
    void testPromptPermanentEffectSelection() {
        List<String> effects = new ArrayList<>();
        effects.add("Effect1");
        effects.add("Effect2");
        
        // Similarly, just verify no exceptions
        assertDoesNotThrow(() -> gameIO.promptPermanentEffectSelection(effects));
    }

    @Test
    void testDisplayEntityStats() {
        Player player = new Player(100, 0, 0, null);
        
        assertDoesNotThrow(() -> gameIO.displayEntityStats(player));
    }

    @Test
    void testDisplayMessage() {
        String testMessage = "Test Message";
        assertDoesNotThrow(() -> gameIO.displayMessage(testMessage));
    }

    @Test
    void testGetInput() {
        // Since we can't simulate user input easily without mocking,
        // we'll just verify the method exists and returns without throwing exceptions
        assertDoesNotThrow(() -> gameIO.getInput());
    }

    @Test
    void testResetGameIO() {
        GameIO originalInstance = GameIO.getInstance();
        resetGameIOInstance();
        GameIO newInstance = GameIO.getInstance();
        assertNotSame(originalInstance, newInstance);
    }

    // Helper method to reset singleton instance for testing
    private void resetGameIOInstance() {
        try {
            java.lang.reflect.Field instanceField = GameIO.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            fail("Failed to reset GameIO singleton instance");
        }
    }
}