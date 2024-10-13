package card.defend;

import card.ICard;
import entity.Entity;

public abstract class DefendCard implements ICard {
	private double block;

	public DefendCard(double block) {
        this.block = block;
    }

	@Override
	public void use(Entity user, Entity target) {
		user.increaseDefense(block);
	}

	@Override
	public abstract String getName();

    @Override
	public abstract String getDescription();

}
