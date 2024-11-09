package game;

import java.util.*;

import battle.Battle;
import card.CardFactory;
import effect.*;
import entity.*;
import gameIO.GameIO;
import card.*;

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
		this.player = new Player(300, 0, 0, createPlayerInitialDeck());
		initializePlayerEffect();
	}

	private void initializePlayerEffect() {
		// Create a map of effects and their corresponding implementations
		Map<String, Runnable> effects = new LinkedHashMap<>();
		effects.put("Extra Max Health (Permanent)",
				() -> player.addPermanentEffectInRounds(permanentEffectFactory.createEffect("PermanentExtraMaxHealthEffect")));
		effects.put("Extra Defense (Permanent)",
				() -> player.addPermanentEffectInRounds(permanentEffectFactory.createEffect("PermanentExtraDefenseEffect")));

		// Prompt the user to choose an effect
		String choice = gameIO.promptPermanentEffectSelection(new ArrayList<>(effects.keySet()));

		// Execute the chosen effect
		effects.get(choice).run();
	}
	
	private List<ICard> createPlayerInitialDeck() {
		List<ICard> deck = new ArrayList<>();
		CardFactory cardFactory = CardFactory.getInstance();
		deck.add(cardFactory.createCard(CardName.ATTACK_BASIC));
		deck.add(cardFactory.createCard(CardName.ATTACK_BASIC));
		deck.add(cardFactory.createCard(CardName.ATTACK_BASIC));
		deck.add(cardFactory.createCard(CardName.DEFEND_BASIC));
		deck.add(cardFactory.createCard(CardName.DEFEND_BASIC));
		deck.add(cardFactory.createCard(CardName.DEFEND_BASIC));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_HEAL));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_HEAL));
		return deck;
	}

	private void initializeFoes() {
		
		this.foes = new ArrayList<>();
		this.foes.add(new Foe("Goblin", 100, 5, 5));
		this.foes.add(new Foe("Orc", 200, 10, 10));
		this.foes.add(new Foe("Troll", 300, 15, 15));
		this.foes.add(new Foe("Dragon", 400, 20, 20));
		this.foes.add(new Foe("Demon", 500, 25, 25));
		this.foes.add(new Foe("Death Knight", 600, 30, 30));
		this.foes.add(new Foe("Lich", 700, 35, 35));
		this.foes.add(new Foe("Beholder", 800, 40, 40));
		this.foes.add(new Foe("Mind Flayer", 900, 45, 45));
		this.foes.add(new Foe("Ancient Red Dragon", 1000, 50, 50));
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
