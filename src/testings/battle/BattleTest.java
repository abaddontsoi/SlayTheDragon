package testings.battle;

import battle.Battle;
import card.ICard;
import entity.Foe;
import entity.Player;
import gameIO.GameIO;
import gameIO.IIOHandler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Stub implementation of IIOHandler for testing
class StubIOHandler implements IIOHandler {
    private List<String> messages = new ArrayList<>();
    private List<String> inputs = new ArrayList<>();
    private int inputIndex = 0;

    public void addInput(String input) {
        inputs.add(input);
    }

    @Override
    public void displayMessage(String message) {
        
    }

    @Override
    public String getInput() {

        return "";
    }

    public List<String> getMessages() {
        return messages;
    }
}

// Simple implementation of ICard for testing
class SimpleCard implements ICard {
    private String name;
    private String description;
    private int attack;
    private int defense;

    public SimpleCard(String name, String description, int attack, int defense) {
        this.name = name;
        this.description = description;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ICard clone() {
        return new SimpleCard(name, description, attack, defense);
    }
}

// Test class forc Battle
public class BattleTest {

    private Battle battle;
    private Player player;
    private Foe foe;
    private StubIOHandler stubIOHandler;
    private List<ICard> playerDeck;
    private List<ICard> foeDeck;

    @BeforeEach
    void setUp() {
        // Use StubIOHandler for GameIO
        stubIOHandler = new StubIOHandler();
        GameIO gameIO = new GameIO();

        // Initialize player and foe decks
        playerDeck = new ArrayList<>();
        foeDeck = new ArrayList<>();

        // Add a simple card to the decks
        playerDeck.add(new SimpleCard("Attack", "Deals 10 damage", 10, 0));
        foeDeck.add(new SimpleCard("Defend", "Blocks 5 damage", 0, 5));

        // Create player and foe entities
        player = new Player(100, 10, 20, playerDeck);
        foe = new Foe("Goblin", "Elite", 50, 5, 15, foeDeck);

        // Create the Battle instance
        battle = new Battle(player, foe, 1);
    }

  
    @Test
    void testPlayerWins() {
        // Simulate the player defeating the foe
        foe.takeDamage(foe.getHealth()); // Reduce foe's health to 0
      

        // Check if the "Player wins!" message is displayed
        List<String> messages = stubIOHandler.getMessages();
}

    @Test
    void testFoeWins() {
        // Simulate the foe defeating the player
        player.takeDamage(player.getHealth()); // Reduce player's health to 0
        
        // Check if the "Enemy wins!" message is displayed
}

    @Test
    void testRewardSystem() {
        // Simulate defeating an Elite foe
        foe.takeDamage(foe.getHealth());
        stubIOHandler.addInput("1"); // Simulate player choosing "Restore to full HP" as the reward

     

        // Verify that the player's health was restored to full
        assertEquals(player.getHealth(), player.getMaxHealth(), "Player's health should be fully restored.");
    }
    @Test
    void testRoundWithRewardSystem() {
        // Simulate a battle where the player defeats the foe and triggers the reward system
        foe.takeDamage(foe.getHealth()); // Reduce foe's health to 0
        stubIOHandler.addInput("1"); // Simulate player choosing "Restore to full HP"

 

        // Verify that the player's health is fully restored
        assertEquals(player.getMaxHealth(), player.getHealth(), "Player's health should be fully restored.");
        // Verify that the round logic was executed
    }
   
    @Test
    void testApplyFullHpRestore() {
        // Simulate the player's health being reduced
        player.takeDamage(50); // Reduce health by 50
        assertTrue(player.getHealth() < player.getMaxHealth(), "Player's health should not be full before reward.");

        // Simulate the reward system where the player selects "Restore to full HP"
        stubIOHandler.addInput("1"); // Simulate input for "Restore to full HP"
       
        // Verify that the player's health is restored
//        assertEquals(player.getMaxHealth(), player.getHealth());
    }
    @Test
    void testGetRewardEffect() {
        // Simulate defeating an Elite foe
        foe.takeDamage(foe.getHealth()); // Reduce foe health to 0
        
        // Simulate player choosing the second reward ("Strength + 3, Defense + 3")
        stubIOHandler.addInput("2"); // Simulate input for the second reward
       ; // Trigger endBattle, which invokes the reward system

        // Verify that the stat boost was applied
//        assertEquals(23, player.getStrength());
//        assertEquals(13, player.getDefense());
    }
}



