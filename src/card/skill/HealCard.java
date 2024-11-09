package card.skill;

import entity.Entity;

abstract public class HealCard extends SkillCard {
	protected int healAmount;
	
	public HealCard(int healAmount) {
		this.healAmount = healAmount;
	}

	@Override
	public void play(Entity caster, Entity target) {
		caster.heal(healAmount);
	}
}
