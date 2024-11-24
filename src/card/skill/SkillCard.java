package card.skill;

import battle.ComputeCenter;
import card.ICard;
import entity.Entity;

public abstract class SkillCard implements ICard {

	public SkillCard() {
	}
	
	public abstract void play(Entity target, ComputeCenter cal);
	
	@Override
	public SkillCard clone() {
		try {
			return (SkillCard) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
