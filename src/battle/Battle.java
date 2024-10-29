package battle;

import entity.*;
import record.BattleRecord;
import record.Record;
import gameIO.GameIO;

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
		
        // Display player's available cards and prompt the player to choose a card
        player.chooseCard(enemy);
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
        
        // Enemy AI logic to choose and perform an action
        enemy.chooseCard(player);
        // After choosing and execute card, create a new turn data and push to battle record;
        
        gameIO.displayMessage("=====================================================");
    }

    private void applyEffects(Entity entity) {
        entity.applyEffects();
    }

    private void endBattle() {
        if (player.isAlive()) {
        	gameIO.displayMessage("Player wins!");
            // Reward player or progress to next level
        } else {
        	gameIO.displayMessage("Enemy wins!");
            // Handle game over logic
        }
    }
}
