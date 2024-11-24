package game;

import java.util.*;

import battle.Battle;
import battle.record.BattleRecord;
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
				() -> player.addPermanentEffectInRounds(
						permanentEffectFactory.createEffect("PermanentExtraMaxHealthEffect")));
		effects.put("Extra Defense (Permanent)",
				() -> player.addPermanentEffectInRounds(
						permanentEffectFactory.createEffect("PermanentExtraDefenseEffect")));

		// Prompt the user to choose an effect
		String choice = gameIO.promptPermanentEffectSelection(new ArrayList<>(effects.keySet()));

		// Execute the chosen effect
		effects.get(choice).run();
	}

	private List<ICard> createPlayerInitialDeck() {
		List<ICard> deck = new ArrayList<>();
		CardFactory cardFactory = CardFactory.getInstance();
		for (int i = 0; i < 3; i++) {
			deck.add(cardFactory.createCard(CardName.ATTACK_BASIC));
			deck.add(cardFactory.createCard(CardName.DEFEND_BASIC));
		}
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_HEAL));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_HEAL));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_POISON));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_DRAW));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_DRAW));
		deck.add(cardFactory.createCard(CardName.SKILL_BASIC_DRAW));
		return deck;
	}

	private void initializeFoes() {
		CardFactory cardFactory = CardFactory.getInstance();
		List<ICard> normalFoeDeck = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			normalFoeDeck.add(cardFactory.createCard(CardName.ATTACK_BASIC));
			normalFoeDeck.add(cardFactory.createCard(CardName.DEFEND_BASIC));
		}
		List<ICard> EliteFoeDeck = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			EliteFoeDeck.add(cardFactory.createCard(CardName.ATTACK_ADVANCED));
			EliteFoeDeck.add(cardFactory.createCard(CardName.DEFEND_ADVANCED));
		}
		List<ICard> BossDeck = new ArrayList<>();
		this.foes = new ArrayList<>();
		this.foes.add(new Foe("Orc", "Normal", 100, 5, 5, normalFoeDeck));
		this.foes.add(new Foe("Goblin", "Normal", 200, 10, 10, normalFoeDeck));
		this.foes.add(new Foe("idk1", "Elite", 300, 15, 15, EliteFoeDeck));
		this.foes.add(new Foe("idk2", "Normal", 400, 20, 20, normalFoeDeck));
		this.foes.add(new Foe("idk3", "Normal", 500, 25, 25, normalFoeDeck));
		this.foes.add(new Foe("Death Knight", "Elite", 600, 30, 30, EliteFoeDeck));
		this.foes.add(new Foe("Lich", "Normal", 700, 35, 35, normalFoeDeck));
		this.foes.add(new Foe("Beholder", "Normal", 800, 40, 40, normalFoeDeck));
		this.foes.add(new Foe("Mind Flayer", "Elite", 900, 45, 45, EliteFoeDeck));
		this.foes.add(new Foe("Ancient Red Dragon", "Boss", 1000, 50, 50, BossDeck));
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
			BattleRecord.printAllFacedFoes();
		}
	}
}
