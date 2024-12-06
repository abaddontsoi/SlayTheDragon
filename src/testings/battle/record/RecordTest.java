package testings.battle.record;

import static org.junit.jupiter.api.Assertions.*;
import battle.*;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import battle.record.Record;
import battle.record.BattleRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battle.FoeData;
import battle.PlayerData;
import card.ICard;
class RecordTest {

    private PlayerData mockPlayerData1;
    private PlayerData mockPlayerData2;
    private FoeData mockFoeData1;
    private FoeData mockFoeData2;
    private ICard mockCard1;
    private ICard mockCard2;
    private Entity mockEntity1;
    private Entity mockEntity2;
    private Player testPlayer;
    private Foe testFoe;
	private static final double INITIAL_MAX_HEALTH = 100;
	private static final int INITIAL_DEFENSE = 10;
	private static final int INITIAL_STRENGTH = 20;
	private static final List<ICard> INITIAL_DECK = new ArrayList<ICard>();
	private static final String MOCK_ENTITY_NAME = "Mock Entity";
	private class TestEntity extends Entity {
        public TestEntity(double maxHealth, int defense, int strength, List<ICard> deck) {
            super(maxHealth, defense, strength, deck);
        }

        @Override
        public String getName() {
            return MOCK_ENTITY_NAME;
        }
    }
    @BeforeEach
    void setUp() {
        // Mock or stub implementation for PlayerData
    	testPlayer = new Player(INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
    	testFoe = new Foe("Foe","Elite",INITIAL_MAX_HEALTH, INITIAL_DEFENSE, INITIAL_STRENGTH, INITIAL_DECK);
        mockPlayerData1 = new PlayerData(testPlayer);
        mockPlayerData2 = new PlayerData(testPlayer);
        mockPlayerData1.takeDamage(50);
        mockPlayerData2.takeDamage(30);
        mockPlayerData1.addTotalHeal(20);
        mockPlayerData2.addTotalHeal(10);
        for (int i = 0; i < 5; i++) {
            mockPlayerData1.doneRound(); // 5 rounds
        }
        for (int i = 0; i < 3; i++) {
            mockPlayerData2.doneRound(); // 3 rounds
        }

        // Add cards played
        for (int i = 0; i < 10; i++) {
            mockPlayerData1.addTotalCardsPlayed(); // 10 cards
        }
        for (int i = 0; i < 6; i++) {
            mockPlayerData2.addTotalCardsPlayed(); // 6 cards
        }
        mockPlayerData1.addTotalAttackDamage(100);
        mockPlayerData2.addTotalAttackDamage(80);
        mockPlayerData1.addTotalDefense(40);
        mockPlayerData2.addTotalDefense(30);
        mockPlayerData1.addReward(mockCard1);
        mockPlayerData2.addReward(mockCard2);

        // Mock or stub implementation for FoeData
        mockFoeData1 = new FoeData(testFoe);
        mockFoeData2 = new FoeData(testFoe);

        // Mock or stub implementation for ICard
        mockCard1 = new ICard() {
            @Override
            public String getName() {
                return "MockCard1";
            }

            @Override
            public String getDescription() {
                return "This is a mock card for testing.";
            }

            @Override
            public ICard clone() {
                return this; // Return the same instance for simplicity
            }
        };

        mockCard2 = new ICard() {
            @Override
            public String getName() {
                return "MockCard2";
            }

            @Override
            public String getDescription() {
                return "This is another mock card for testing.";
            }

            @Override
            public ICard clone() {
                return this; // Return the same instance for simplicity
            }
        };
        
        mockEntity1 = new TestEntity(INITIAL_MAX_HEALTH,INITIAL_DEFENSE,INITIAL_STRENGTH,INITIAL_DECK); // Properly initialize this
        mockEntity2 = new TestEntity(INITIAL_MAX_HEALTH,INITIAL_DEFENSE,INITIAL_STRENGTH,INITIAL_DECK); // Properly initialize this        

        // Create records
        BattleRecord.createRecord(mockPlayerData1, mockFoeData1);
        BattleRecord.createRecord(mockPlayerData2, mockFoeData2);
    }

    @Test
    void testTotalDamageReceived() {
        int totalDamageReceived = Record.getTotalDamageReceived();
        assertEquals(240, totalDamageReceived, "Total damage received should be the sum of all damage received by the player.");
    }

    @Test
    void testTotalHealingInGame() {
        double totalHealing = Record.getTotalHealingInGame();
        assertEquals(150, totalHealing, "Total healing in the game should be the sum of all healing by the player.");
    }

    @Test
    void testMaxRoundsInBattle() {
        int maxRounds = Record.getMaxRoundsInBattle();
        assertEquals(5, maxRounds, "Maximum rounds in a battle should be correct.");
    }

    @Test
    void testMinRoundsInBattle() {
        int minRounds = Record.getMinRoundsInBattle();
        assertEquals(3, minRounds, "Minimum rounds in a battle should be correct.");
    }

    @Test
    void testMaxCardsPlayedInBattle() {
        int maxCardsPlayed = Record.getMaxCardsPlayedInBattle();
        assertEquals(10, maxCardsPlayed, "Maximum cards played in a battle should be correct.");
    }

    @Test
    void testMinCardsPlayedInBattle() {
        int minCardsPlayed = Record.getMinCardsPlayedInBattle();
        assertEquals(6, minCardsPlayed, "Minimum cards played in a battle should be correct.");
    }

    @Test
    void testAllRewards() {
        List<ICard> rewards = Record.getAllRewards();
        assertEquals(22, rewards.size(), "The total number of rewards collected should be correct.");
        assertTrue(rewards.contains(mockCard1), "Rewards should include MockCard1.");
        assertTrue(rewards.contains(mockCard2), "Rewards should include MockCard2.");
    }

    @Test
    void testAllRewardsString() {
        String rewardsString = Record.allRewardsString();
        assertEquals("MockCard1, MockCard2", rewardsString, "The rewards string should list all collected rewards.");
    }

    @Test
    void testAllFacedFoes() {
        List<Entity> facedFoes = Record.getAllFacedFoes();
        assertEquals(14, facedFoes.size(), "The total number of foes faced should be correct.");
        assertTrue(facedFoes.contains(mockEntity1), "Faced foes should include MockFoe1.");
        assertTrue(facedFoes.contains(mockEntity2), "Faced foes should include MockFoe2.");
    }

    @Test
    void testAllFacedFoesString() {
        String foesString = Record.getAllFacedFoesString();
        String expected = "Faced foes: \nMockFoe1\nMockFoe2\n";
        assertEquals(expected, foesString, "The faced foes string should list all foes faced.");
    }

    @Test
    void testPlayerTotalDamage() {
        int totalDamage = Record.getPlayerTotalDamage();
        assertEquals(1800, totalDamage, "Total damage dealt by the player should be correct.");
    }

    @Test
    void testPlayerTotalDefense() {
        int totalDefense = Record.getPlayerTotalDefense();
        assertEquals(840, totalDefense, "Total defense obtained by the player should be correct.");
    }

    @Test
    void testGetReportString() {
        String report = Record.getReportString();
        assertNotNull(report, "The report string should not be null.");
        assertTrue(report.contains("Total damage dealt:"), "The report should include total damage dealt.");
        assertTrue(report.contains("Total healing:"), "The report should include total healing.");
        assertTrue(report.contains("Maximum rounds in a battle:"), "The report should include maximum rounds in a battle.");
        assertTrue(report.contains("Minimum rounds in a battle:"), "The report should include minimum rounds in a battle.");
//        assertTrue(report.contains("MockCard1, MockCard2"), "The report should include all rewards collected.");
        assertTrue(report.contains("Faced foes:"), "The report should include all faced foes.");
    }
}