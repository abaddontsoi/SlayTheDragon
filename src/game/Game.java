package game;

import java.util.*;

import battle.Battle;
import card.CardFactory;
import effect.*;
import entity.*;
import gameIO.GameIO;

public class Game {
	private static Game instance = null;
	private Player player;
	private List<Foe> foes;
	private int currentLevel;
	private ConcretePermanentEffectFactory permanentEffectFactory;
	private GameIO gameIO;

	private Game() {
		this.currentLevel = 1;
		this.permanentEffectFactory = new ConcretePermanentEffectFactory();
		this.gameIO = GameIO.getInstance();
		this.initializePlayer();
		this.initializeFoes();
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	private void initializePlayer() {
		this.player = new Player(300, 0, 0, CardFactory.createRandomDeck());
		// Add effects to the player
//		Currently this function has some bugs
//		- But I am lazy to fix it now
//		- I will fix it later
//		initializePlayerEffect();
	}

	private void initializePlayerEffect() {
		// Create a map of effects and their corresponding implementations
		Map<String, Runnable> effects = new LinkedHashMap<>();
		effects.put("Extra Max Health (Permanent)",
				() -> player.addEffect(permanentEffectFactory.createEffect("PermanentExtraMaxHealthEffect")));
		effects.put("Extra Defense (Permanent)",
				() -> player.addEffect(permanentEffectFactory.createEffect("PermanentExtraDefenseEffect")));

		// Prompt the user to choose an effect
		String choice = gameIO.promptPermanentEffectSelection(new ArrayList<>(effects.keySet()));

		// Execute the chosen effect
		effects.get(choice);
	}

	private void initializeFoes() {
		this.foes = new ArrayList<>();
		this.foes.add(new Foe("Goblin", 100, 5, 5, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Orc", 200, 10, 10, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Troll", 300, 15, 15, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Dragon", 400, 20, 20, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Demon", 500, 25, 25, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Death Knight", 600, 30, 30, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Lich", 700, 35, 35, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Beholder", 800, 40, 40, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Mind Flayer", 900, 45, 45, CardFactory.createRandomDeck()));
		this.foes.add(new Foe("Ancient Red Dragon", 1000, 50, 50, CardFactory.createRandomDeck()));
	}

	public void startGame() {
		while (player.isAlive() && currentLevel <= 10) {
			Foe currentFoe = foes.get(currentLevel - 1);
			Battle battle = new Battle(player, currentFoe);
			battle.startBattle();

			if (player.isAlive()) {
				currentLevel++;
				// Optional: Reward player, increase stats, etc.
			}
		}
		endGame();
	}

	public void endGame() {
		if (player.isAlive()) {
			gameIO.displayMessage("Congratulations! You have defeated all foes!");
		} else {
			gameIO.displayMessage("Game over! You have been defeated!");
		}
	}
}
