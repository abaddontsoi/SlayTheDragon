package card.skill;

import card.ICard;
import entity.Entity;

public abstract class SkillCard implements ICard {
	
	public SkillCard() {
	}

	@Override
	public abstract void use(Entity user, Entity target);

	@Override
	public abstract String getName();

    @Override
	public abstract String getDescription();
}
