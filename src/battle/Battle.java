package battle;

import entity.*;
import gameIO.GameIO;

public class Battle {
	private Player player;
    private Foe enemy;
    private boolean isPlayerTurn;
    private GameIO gameIO;

	public Battle(Player player, Foe enemy) {
		this.player = player;
		this.enemy = enemy;
		// Player goes first
		this.isPlayerTurn = true;
		this.gameIO = GameIO.getInstance();
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
		gameIO.displayPlayerTurn();
        // Display player's options, e.g., available cards
        // Let the player choose an action and perform it
        player.chooseAction(enemy);
        applyEffects(player);
    }

    private void enemyTurn() {
    	gameIO.displayEnemyTurn();
        // Enemy AI logic to choose and perform an action
        enemy.chooseAction(player);
        applyEffects(enemy);
    }

    private void applyEffects(Entity entity) {
        entity.applyEffects();
        // Handle any additional effect logic, like poison damage or regeneration
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
