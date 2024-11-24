package battle;

import entity.*;
import gameIO.GameIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import battle.record.BattleRecord;
import card.*;

public class Battle {
    private Player player;
    private Foe enemy;
    private boolean isPlayerTurn;
    private GameIO gameIO;
    private Calculator calculator;
    private CardManager playerCardManager;
    private CardManager foeCardManager;
    private int round;
    // private List<RoundRecord> roundRecords;

    public Battle(Player player, Foe enemy) {
        this.player = player;
        this.enemy = enemy;
        // Player goes first
        this.isPlayerTurn = true;
        this.gameIO = GameIO.getInstance();
        this.calculator = new Calculator(player, enemy);
        this.playerCardManager = new CardManager(player.getDeck(), player);
        this.foeCardManager = new CardManager(enemy.getDeck(), enemy);
        this.round = 1;
        // this.roundRecords = new ArrayList<>();
    }

    public void startBattle() {
        // Display battle start message
        CardFactory cardFactory = CardFactory.getInstance();
        gameIO.displayBattleStart(player, enemy);
        while (player.isAlive() && enemy.isAlive()) {
            round();
            // RoundRecord roundRecord = round();
            // roundRecords.add(RoundRecord);

            // if (isPlayerTurn) {
            // playerTurn();
            // } else {
            // enemyTurn();
            // }
            // isPlayerTurn = !isPlayerTurn;
        }
        endBattle();
    }

    private void round() {
        List<ICard> eCards;
        List<ICard> pCards;
        gameIO.displayMessage("\n=========================== Round " + round + " ===========================");
        calculator.applyPoisonEffect();
        if (player.isAlive() && enemy.isAlive()) {
            applyEffects(enemy);
            applyEffects(player);
            gameIO.displayEntityStats(enemy);
            gameIO.displayEntityEffects(enemy);
            foeCardManager.initializeTurn();
            eCards = foeCardManager.chooseCards(calculator);
            gameIO.displayMessage(enemy.getName() + " played cards:");

            eCards.forEach((card) -> {
                gameIO.displayMessage(card.getName() + " - " + card.getDescription());
            });
            calculator.calculateFoeRound(eCards);

            gameIO.displayMessage("\n=========================== Your Action: ===========================");
            gameIO.displayEntityStats(player);
            gameIO.displayEntityEffects(player);
            playerCardManager.initializeTurn();
            pCards = playerCardManager.chooseCards(calculator);

            gameIO.displayMessage("=========================== End Round " + round + " ===========================");
            calculator.finishPlayerRound();
            round++;
        }

    }

    private void applyEffects(Entity entity) {
        entity.applyEffects();
    }

    private void rewardPlayer() {
        // Reward player, add a card from to pool to the player's deck
        CardPool cardPool = CardPool.getInstance();
        ICard card = cardPool.getRandomCard();
        player.addCardToDeck(card);
        calculator.addPlayerReward(card);
    }

    private void endBattle() {
        // BattleRecord battleRecord = new BattleRecord()
    	
        // Generate BattleRecord
    	calculator.genBattleRecord();

        if (player.isAlive()) {
            gameIO.displayMessage("Player wins!");
            rewardPlayer();            
        } else {
            gameIO.displayMessage("Enemy wins!");
            // Handle game over logic
        }
        
    }
}
