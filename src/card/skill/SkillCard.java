package card.skill;

import battle.Calculator;
import card.ICard;
import entity.Entity;

public abstract class SkillCard implements ICard {

	public SkillCard() {
	}
	
	public abstract void play(Entity user, Entity target, Calculator cal);
	
	@Override
	public SkillCard clone() {
		try {
			return (SkillCard) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
