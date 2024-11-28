package battle;

import entity.*;
import gameIO.GameIO;
import gameIO.IIOHandler;

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
    private ComputeCenter calculator;
    private CardManager playerCardManager;
    private CardManager foeCardManager;
    private int level;
    private int round;

    public Battle(Player player, Foe enemy, int level) {
        this.player = player;
        this.enemy = enemy;
        // Player goes first
        this.isPlayerTurn = true;
        this.gameIO = GameIO.getInstance();
        this.calculator = new ComputeCenter(player, enemy);
        this.playerCardManager = new CardManager(player.getDeck(), player);
        this.foeCardManager = new CardManager(enemy.getDeck(), enemy);
        this.level = level;
        this.round = 1;
    }

    public void startBattle() {
        // Display battle start message
        CardFactory cardFactory = CardFactory.getInstance();
        gameIO.displayBattleStart(player, enemy, level);
        while (player.isAlive() && enemy.isAlive()) {
            round();
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
            // gameIO.displayEntityEffects(enemy);
            foeCardManager.initializeTurn();
            eCards = foeCardManager.chooseCards(calculator);
            gameIO.displayMessage(enemy.getName() + " played cards:");

            eCards.forEach((card) -> {
                gameIO.displayMessage(card.getName() + " - " + card.getDescription());
            });
            calculator.calculateFoeRound(eCards);

            gameIO.displayMessage("\n=========================== Your Action: ===========================");
            gameIO.displayEntityStats(player);
            // gameIO.displayEntityEffects(player);
            playerCardManager.initializeTurn();
            pCards = playerCardManager.chooseCards(calculator);
            if (enemy.isAlive()) {
                gameIO.displayMessage(
                        "=========================== End Round " + round + " ===========================");
                round++;
            }
            calculator.finishPlayerRound();
        }

    }

    private void applyEffects(Entity entity) {
        entity.applyEffects();
    }

    private void applyFullHpRestore() {
        // Assuming you have a method to restore HP
        player.restoreFullHP();
        gameIO.displayMessage("Your HP has been fully restored.");
    }

    private void applyStatBoosts() {
        // Assuming you have methods to increase strength and defense
        player.increaseStrength(3);
        player.increaseDefense(3);
        gameIO.displayMessage("Your Strength and Defense have been increased by 3.");
    }

    private void getRewardEffect() {
        int index = -1;
        ArrayList<String> outputMsg = new ArrayList<String>();
        outputMsg.add("Restore to full HP");
        outputMsg.add("Strenth + 3, Defense + 3");
        if (enemy.getType().equals("Elite")) {
            gameIO.displayMessage("================================================");
            gameIO.displayMessage("You defeat a elite enemy please choose a effect!");
            for (int i = 1; i <= outputMsg.size(); i++) {
                gameIO.displayMessage(i + ". " +outputMsg.get(i-1));
            }
            while (index < 1 || index > 2) {
                gameIO.displayMessage("Enter the number of your choice:");
                try {
                    index = Integer.parseInt(gameIO.getInput());
                } catch (NumberFormatException e) {
                    gameIO.displayMessage("Invalid input. Please enter a number.");
                }
            }
        }

        switch (index) {
            case 1:
                gameIO.displayMessage("You chose: " + outputMsg.get(0));
                applyFullHpRestore();
                break;
            case 2:
                gameIO.displayMessage("You chose: " + outputMsg.get(1));
                applyStatBoosts();
                break;
            default:
                gameIO.displayMessage("Invalid choice"); // Handle unexpected case
                break;
        }
    }

    private void rewardPlayer() {
        // Reward player, add a card from to pool to the player's deck
        CardPool cardPool = CardPool.getInstance();
        ICard card = cardPool.getRwardCard(gameIO);
        getRewardEffect();
        player.addCardToDeck(card);
        calculator.addPlayerReward(card);
    }

    private void endBattle() {
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
