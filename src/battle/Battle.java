package battle;

import entity.*;
import record.BattleRecord;
import record.Record;
import gameIO.GameIO;

import java.util.List;

import card.*;

public class Battle {
	private Player player;
    private Foe enemy;
    private boolean isPlayerTurn;
    private GameIO gameIO;
    private BattleRecord battleRecord;

	public Battle(Player player, Foe enemy) {
		this.player = player;
		this.enemy = enemy;
		// Player goes first
		this.isPlayerTurn = true;
		this.gameIO = GameIO.getInstance();
		this.battleRecord = new BattleRecord(player.getEntityStatus(), enemy.getEntityStatus());
	}
	
	public void startBattle() {
		// Display battle start message
        CardFactory cardFactory = CardFactory.getInstance();
		gameIO.displayBattleStart(player, enemy);
        while (player.isAlive() && enemy.isAlive()) {
            if (isPlayerTurn) {
                playerTurn();
            } else {
                enemyTurn();
            }
            isPlayerTurn = !isPlayerTurn;
        }
        
        endBattle();
    }


	private void playerTurn() {
		applyEffects(player);
		// Display player's turn message
		gameIO.displayPlayerTurn();
    	gameIO.displayEntityStats(player);
    	gameIO.displayEntityStats(enemy);
    	gameIO.displayEntityEffects(player);
    	gameIO.displayEntityEffects(enemy);
		
    	// Initialise the player's turn
		player.initializeTurn();
		
        // Display player's available cards and prompt the player to choose 3 cards
        List<ICard> chosenCards = player.chooseCards();
        // After choosing and execute card, create a new turn data and push to battle record;
        
        gameIO.displayMessage("=====================================================");
    }

    private void enemyTurn() {
    	applyEffects(enemy);
    	// Display enemy's turn message
    	gameIO.displayEnemyTurn();
    	gameIO.displayEntityStats(player);
    	gameIO.displayEntityStats(enemy);
    	gameIO.displayEntityEffects(player);
    	gameIO.displayEntityEffects(enemy);
    	
    	// Initialise the enemy's turn
        enemy.initializeTurn();
        
        // Enemy AI logic to choose cards
       List<ICard> chosenCards = enemy.chooseCards();
        
        // After choosing and execute card, create a new turn data and push to battle record;
        
        gameIO.displayMessage("=====================================================");
    }

    private void applyEffects(Entity entity) {
        entity.applyEffects();
    }
    
	private void rewardPlayer() {
		// Reward player, add a card from to pool to the player's deck
		CardPool cardPool = CardPool.getInstance();
		ICard card = cardPool.getRandomCard();
		player.addCardToDeck(card);
	}

    private void endBattle() {
        if (player.isAlive()) {
        	gameIO.displayMessage("Player wins!");
            rewardPlayer();
        	
        } else {
        	gameIO.displayMessage("Enemy wins!");
            // Handle game over logic
        }
    }
}
