package entity;

import gameIO.GameIO;

import java.util.*;

import action.*;
import card.ICard;

public class Player extends Entity {
	private GameIO gameIO;

	public Player(double maxHealth, double defense, double strength, GameIO gameIO) {
		super(maxHealth, defense, strength);
		this.gameIO = gameIO;
	}


	@Override
	public void chooseAction(Entity opponent) {
	    // Create a map of actions and their corresponding implementations
	    Map<String, Runnable> actions = new LinkedHashMap<>();
	    actions.put("Attack", () -> new AttackAction(strength).execute(opponent));
	    actions.put("Defend", () -> new DefendAction(defense).execute(this));
	    actions.put("Use Card", () -> {
	        ICard card = gameIO.promptCardSelection(hand);
	        card.use(this, opponent);
	    });
	
	    // Prompt the user to choose an action
	    String choice = gameIO.promptActionSelection(new ArrayList<>(actions.keySet()));
	
	    // Execute the chosen action
	    actions.get(choice).run();
	}

	
	

}
