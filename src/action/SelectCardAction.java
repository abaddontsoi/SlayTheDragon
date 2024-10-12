package action;

import card.ICard;
import entity.Entity;

public class SelectCardAction implements IAction{
	// The player refers to the entity that is selecting a card
	// not necessarily the player entity
	private Entity player;

	public SelectCardAction(Entity player) {
		this.player = player;
	}

	@Override
	public void execute(Entity target) {
		// Logic to prompt player to select a card
        int selectedIndex = promptPlayerForCardSelection();
        if (selectedIndex >= 0 && selectedIndex < player.getHand().size()) {
            ICard selectedCard = player.getHand().get(selectedIndex);
            selectedCard.use(player, target);
            player.getHand().remove(selectedIndex);
        }
	}
	
	private int promptPlayerForCardSelection() {
        // Implement the logic to prompt the player and return the card index
		return 0;
    }

}
