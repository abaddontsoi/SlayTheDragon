package card.defend;

import card.ICard;
import entity.Entity;

public class DefendCard implements ICard {
	private double defense;

	public DefendCard(double defense) {
        this.defense = defense;
    }

	@Override
	public void use(Entity user, Entity target) {
		user.increaseDefense(defense);
	}

	@Override
	public String getName(){
		return null;
	}

    @Override
	public String getDescription(){
		return null;
	}

}
