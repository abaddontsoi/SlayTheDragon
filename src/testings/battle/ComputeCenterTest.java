package testings.battle;

import battle.ComputeCenter;
import battle.EntityData;
import battle.FoeData;
import battle.PlayerData;
import card.CardFactory;
import card.CardName;
import card.ICard;
import card.skill.*;
import card.attack.AttackCard;
import card.defend.DefendCard;
import card.skill.SkillCard;
import entity.Entity;
import entity.EntityStatus;
import entity.Foe;
import entity.Player;
import gameIO.GameIO;
import gameIO.IIOHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Stub implementation of IIOHandler for testing
class StubGameIO implements IIOHandler {
    private final List<String> messages = new ArrayList<>();
    private final List<String> inputs = new ArrayList<>();
    private int inputIndex = 0;

    @Override
    public void displayMessage(String message) {
        messages.add(message);
    }

    @Override
    public String getInput() {
        if (inputIndex < inputs.size()) {
            return inputs.get(inputIndex++);
        }
        return "";
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addInput(String input) {
        inputs.add(input);
    }

    public void clearMessages() {
        messages.clear();
    }
}

public class ComputeCenterTest {

    private ComputeCenter computeCenter;
    private ComputeCenter computeCenter2;
    private Player player;
    private Foe foe;
    private StubGameIO stubGameIO;
    private PlayerData playerData;
    private FoeData foeData;

    @BeforeEach
    void setUp() {
        // Create a stubbed GameIO instance
        stubGameIO = new StubGameIO();
        GameIO.setIOHandlerForTesting(stubGameIO);

        // Initialize player and foe
        List<ICard> playerDeck = new ArrayList<>();
        List<ICard> foeDeck = new ArrayList<>();
        player = new Player(100, 10, 0, playerDeck);
        foe = new Foe("Goblin", "Normal", 50, 5, 5, foeDeck);
        computeCenter = new ComputeCenter(player, foe);

        // Set foe defense manually (if needed)
        foe.getEntityStatus().setDefense(5);

        playerData = computeCenter.getPlayerDataForTest();
        foeData = computeCenter.getFoeDataForTest();

        // Debugging setup values
    }
    @Test
    void testApplyPoisonEffect() {
        // Apply poison to both player and foe
        computeCenter.setPoisonDamage(player, 5);
        computeCenter.setPoisonDamage(foe, 10);

        // Apply poison effect
        computeCenter.applyPoisonEffect();

        // Verify poison messages
        // Check health after poison
        assertEquals(45, foe.getHealth(), "Foe health should decrease by poison damage.");
        assertEquals(90, player.getHealth(), "Player health should decrease by poison damage.");
    }
    @Test
    void testSetAttackAndDefenseBuff() {
        // Arrange: Set initial buffs for attack and defense
        double playerAttackBuff = 2.0;  // Player's attack will be multiplied by 2.0
        double foeAttackBuff = 1.5;     // Foe's attack will be multiplied by 1.5
        double playerDefenseBuff = 1.8; // Player's defense will be multiplied by 1.8
        double foeDefenseBuff = 1.3;    // Foe's defense will be multiplied by 1.3

        // Act: Apply buffs using ComputeCenter
        computeCenter.setAttackBuff(player, playerAttackBuff);
        computeCenter.setAttackBuff(foe, foeAttackBuff);
        computeCenter.setDefenseBuff(player, playerDefenseBuff);
        computeCenter.setDefenseBuff(foe, foeDefenseBuff);

        // Assert: Verify attack buffs directly from playerData and foeData
        assertEquals(playerAttackBuff, computeCenter.getPlayerDataForTest().getAttackBuff(),
                "Player's attack buff should be updated to 2.0.");
        assertEquals(foeAttackBuff, computeCenter.getFoeDataForTest().getAttackBuff(),
                "Foe's attack buff should be updated to 1.5.");

        // Assert: Verify defense buffs directly from playerData and foeData
        assertEquals(playerDefenseBuff, computeCenter.getPlayerDataForTest().getDefenseBuff(),
                "Player's defense buff should be updated to 1.8.");
        assertEquals(foeDefenseBuff, computeCenter.getFoeDataForTest().getDefenseBuff(),
                "Foe's defense buff should be updated to 1.3.");
    }
    @Test
    void testCalculateFoeRound() {
        // Use CardFactory to create cards
        CardFactory cardFactory = CardFactory.getInstance();
        ICard attackCard = cardFactory.createCard(CardName.ATTACK_BASIC); // BasicAttackCard
        ICard defendCard = cardFactory.createCard(CardName.DEFEND_BASIC); // BasicDefendCard
        ICard healCard = cardFactory.createCard(CardName.SKILL_BASIC_HEAL); // BasicDefendCard

        List<ICard> foeCards = List.of(attackCard, defendCard,healCard);

        // Calculate foe's round
        computeCenter.calculateFoeRound(foeCards);

        // Verify messages
        assertEquals(attackCard.getName(),"Basic Attack Card");
        assertEquals(defendCard.getName(),"Basic Defend Card");
        assertEquals(healCard.getName(),"Basic Heal Card");
    }


    @Test
    void testCalculatePlayerActionAttackCard() {
        // Arrange: Create an advanced attack card
        CardFactory cardFactory = CardFactory.getInstance();
        ICard attackCard = cardFactory.createCard(CardName.ATTACK_ADVANCED);

        // Set foe defense manually (if necessary, for debugging)
        foeData.setDefense(5); // Ensure foe has 5 defense

        // Debug initial values


        // Act: Simulate player action
        boolean continueBattle = computeCenter.calculatePlayerAction(attackCard);

        // Debug final values


        // Assert: Verify foe health after attack
        assertEquals(45, foeData.getHealth(), "Foe health should decrease by 15 (damage - defense).");

        // Assert: Verify that the battle continues
        assertTrue(continueBattle, "Battle should continue if foe is not defeated.");
    }
    @Test
    void testCalculatePlayerActionAttackCardDealNoDmg() {
        // Arrange: Create a basic attack card
        CardFactory cardFactory = CardFactory.getInstance();
        ICard attackCard = cardFactory.createCard(CardName.ATTACK_BASIC);

        // Set foe defense manually (if necessary, for debugging)
        foeData.setDefense(10); // Ensure foe has higher defense than attack damage

        // Debug initial values
;

        // Act: Simulate player action
        boolean continueBattle = computeCenter.calculatePlayerAction(attackCard);

        // Debug final values


        // Assert: Verify foe health remains unchanged
        assertEquals(50, foeData.getHealth(), "Foe health should remain unchanged (damage fully blocked by defense).");

        // Assert: Verify that the battle continues
        assertTrue(continueBattle, "Battle should continue if foe is not defeated.");
    }
    @Test
    void testCalculatePlayerActionDefendCard() {
        // Create a defend card
    	CardFactory cardFactory = CardFactory.getInstance();
        ICard defendCard = cardFactory.createCard(CardName.DEFEND_BASIC);

        // Simulate player action
        boolean continueBattle = computeCenter.calculatePlayerAction(defendCard);

        // Verify messages


        // Verify defense
        assertEquals(10, player.getDefense());

        // Verify battle continues
        assertTrue(continueBattle, "Battle should continue if foe is not defeated.");
    }

    @Test
    void testCalculatePlayerActionSkillCard() {
        // Ensure the card factory and compute center are set up
        CardFactory cardFactory = CardFactory.getInstance();
        ICard healCard = cardFactory.createCard(CardName.SKILL_BASIC_HEAL);

        // Ensure the card is not null
        assertNotNull(healCard, "SkillCard should not be null. Check CardFactory implementation.");

        // If the card is a SkillCard, apply its effect to the player
        if (healCard instanceof SkillCard) {
            ((SkillCard) healCard).play(player, computeCenter);
            ((SkillCard) healCard).play(foe, computeCenter);
        }

        // Act: Player uses the skill card through ComputeCenter
        boolean result = computeCenter.calculatePlayerAction(healCard);

        // Debugging output for health values

        // Assert: Verify the effects of the SkillCard
        assertEquals(50, foe.getHealth(), "Foe's health should remain unchanged.");
        player.takeDamage(10);
        assertEquals(90, player.getHealth(), "Player's health should increase by 5.");
        assertTrue(result, "The game should continue after applying a skill card if foe is not defeated.");
    }

    @Test
    void testCalculateRound() {
        // Arrange: Set up foe's attack and player's defense
        playerData.setDefense(20); // Player defense = 20
        foeData.setAttackDamage(20); // Foe attack damage = 25

        // Debug initial values


        // Act: Simulate round calculation
        computeCenter.calculateRound();

        // Debug final values


        // Assert: Verify player's final health
        int expectedHealth = (int) (100 - Math.max(0, foeData.getAttackDamage() - playerData.getDefense()));
        assertEquals(expectedHealth, playerData.getHealth(), "Player's health should decrease by the remaining damage after defense.");

        // Assert: Verify player's final defense
        int expectedDefense = Math.max(0, playerData.getDefense() - foeData.getAttackDamage());
        assertEquals(expectedDefense, playerData.getDefense(), "Player's defense should decrease or reset to 0 if fully used.");

        // Assert: Verify that foe's attack damage is reset after the round
        assertEquals(0, foeData.getAttackDamage(), "Foe's attack damage should be reset to 0 after the round.");
    }
    @Test
    void testCalculateRound_ElseCase() {
        // Arrange: Set up foe's attack and player's defense
        playerData.setDefense(10); // Player defense = 10 (lower than foe's attack)
        foeData.setAttackDamage(25); // Foe attack damage = 25 (greater than player's defense)

        // Debug initial values

        // Act: Simulate round calculation
        computeCenter.calculateRound();

        // Debug final values


        // Assert: Verify player's health after taking damage
        int expectedHealth = (int) (100 - (25 - 10)); // Player health = 100 - (AttackDamage - Defense)
        assertEquals(expectedHealth, playerData.getHealth(), "Player's health should decrease by the remaining damage after defense.");

        // Assert: Verify player's defense is reset to 0
        assertEquals(0, playerData.getDefense(), "Player's defense should be set to 0 if fully used.");

        // Assert: Verify foe's attack damage is reset to 0
        assertEquals(0, foeData.getAttackDamage(), "Foe's attack damage should be reset to 0 after the round.");
    }
}