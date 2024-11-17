package gameIO;

import java.util.List;

import card.ICard;
import effect.EffectInTurns;
import effect.IEffect;
import entity.Entity;
import entity.Foe;
import entity.Player;

public class GameIO {
    private IIOHandler ioHandler;
    private static GameIO instance;

	private GameIO() {
		ioHandler = new ConsoleIOHandler();
	}
	
	public static GameIO getInstance() {
		if (instance == null) {
			instance = new GameIO();
		}
		return instance;
	}
    
    public void displayBattleStart(Player player, Foe foe) {
        ioHandler.displayMessage("\n======== " +foe.getType() + " Battle begins between player and " + foe.getName()+ " ========");
    }

    public void displayPlayerTurn() {
        ioHandler.displayMessage("It's your turn. Choose an action:");
    }

    public void displayEnemyTurn() {
        ioHandler.displayMessage("Enemy's turn.");
    }

    public void displayActionResult(String result) {
        ioHandler.displayMessage(result);
    }
    
	public String promptPermanentEffectSelection(List<String> effects) {
	    ioHandler.displayMessage("Choose an effect:");
	
	    for (int i = 0; i < effects.size(); i++) {
	        ioHandler.displayMessage((i + 1) + ". " + effects.get(i));
	    }
	
	    int choice = -1;
	    while (choice < 1 || choice > effects.size()) {
	        ioHandler.displayMessage("Enter the number of your choice:");
	        try {
	            choice = Integer.parseInt(ioHandler.getInput());
	        } catch (NumberFormatException e) {
	            ioHandler.displayMessage("Invalid input. Please enter a number.");
	        }
	    }
	    
	    String effectName = effects.get(choice - 1);
	    ioHandler.displayMessage("You have chosen: " + effectName);
	
	    return effectName;  // Return the chosen effect name
	}

	public ICard promptCardSelection(List<ICard> hand) {
	    ioHandler.displayMessage("Choose a card:");
	
	    for (int i = 0; i < hand.size(); i++) {
	        ioHandler.displayMessage((i + 1) + ". " + hand.get(i).getName() + " - " + hand.get(i).getDescription());
	    }
	
	    int choice = -1;
	    while (choice < 1 || choice > hand.size()) {
	        ioHandler.displayMessage("Enter the number of your choice:");
	        try {
	            choice = Integer.parseInt(ioHandler.getInput());
	        } catch (NumberFormatException e) {
	            ioHandler.displayMessage("Invalid input. Please enter a number.");
	        }
	    }
	
	    return hand.get(choice - 1);  // Return the chosen card
	}
	
	public void displayEntityStats(Entity entity) {
		// Display the entity's health/max health, defense, and strength
		String entityName = entity.getName();
		String message = entityName + " Stats: Health - " + entity.getHealth() + "/" + entity.getMaxHealth()
				+ ", Defense - " + entity.getDefense()
				+ ", Strength - " + entity.getStrength();
		
		ioHandler.displayMessage(message);
	}
	

	public void displayEntityEffects(Entity entity) {
	    // Format the effects, showing the name and remaining duration
	    // of each effect
		String entityName = entity.getName();
	    StringBuilder effects = new StringBuilder(entityName + " Effects: ");
	    List<EffectInTurns> entityEffects = entity.getEffects();
	
	    for (int i = 0; i < entityEffects.size(); i++) {
	        IEffect effect = entityEffects.get(i);
	        String formattedEffectInfo = effect.getFormattedEffectInfo();
	
	        effects.append(formattedEffectInfo);
	
	        // Only append the delimiter if this is not the last effect
	        if (i < entityEffects.size() - 1) {
	            effects.append(" | ");
	        }
	    }
	
	    ioHandler.displayMessage(effects.toString());
	}
	
	public void displayExpireEffectMessage(Entity entity, IEffect effect) {
		ioHandler.displayMessage(entity.getName() + " effect expired: " + effect.getName());
	}
	
	public void displayEffectStackMessage(IEffect effect) {
		ioHandler.displayMessage("Effect stacked: " + effect.getName());
	}
	
	public void displayEffectApplyMessage(Entity entity, IEffect effect) {
		ioHandler.displayMessage(entity.getName() + " effect applied: " + effect.getName());
	}
	
    
	public void displayMessage(String message) {
		ioHandler.displayMessage(message);
	}
}