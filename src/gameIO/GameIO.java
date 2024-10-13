package gameIO;

import java.util.List;

import card.ICard;
import entity.Foe;
import entity.Player;

public class GameIO {
    private IIOHandler ioHandler;
    private static GameIO instance;

    private GameIO(IIOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }
    
	public static GameIO getInstance() {
		if (instance == null) {
			instance = new GameIO(new ConsoleIOHandler());
		}
		return instance;
	}

    public void displayBattleStart(Player player, Foe foe) {
        ioHandler.displayMessage("Battle begins between player and " + foe.getName());
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





	public String promptActionSelection(List<String> actions) {
	    ioHandler.displayMessage("Choose an action:");
	
	    for (int i = 0; i < actions.size(); i++) {
	        ioHandler.displayMessage((i + 1) + ". " + actions.get(i));
	    }
	
	    int choice = -1;
	    while (choice < 1 || choice > actions.size()) {
	        ioHandler.displayMessage("Enter the number of your choice:");
	        try {
	            choice = Integer.parseInt(ioHandler.getInput());
	        } catch (NumberFormatException e) {
	            ioHandler.displayMessage("Invalid input. Please enter a number.");
	        }
	    }
	
	    String actionName = actions.get(choice - 1);
	    ioHandler.displayMessage("You have chosen: " + actionName);
	    
	    return actionName;  // Return the chosen action name
	}


	public ICard promptCardSelection(List<ICard> hand) {
	    ioHandler.displayMessage("Choose a card:");
	
	    for (int i = 0; i < hand.size(); i++) {
	        ioHandler.displayMessage((i + 1) + ". " + hand.get(i).getName());
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


    
	public void displayMessage(String message) {
		ioHandler.displayMessage(message);
	}
}