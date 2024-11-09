package card.skill;

import card.ICard;
import entity.Entity;

abstract class SkillCard implements ICard {

	public SkillCard() {
	}
	
	public abstract void play(Entity user, Entity target);
	
	@Override
	public SkillCard clone() {
		try {
			return (SkillCard) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
