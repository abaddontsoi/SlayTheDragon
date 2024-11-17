package card.skill;

import battle.Calculator;
import entity.Entity;

public abstract class HealCard extends SkillCard {
	protected int healAmount;
	
	public HealCard(int healAmount) {
		this.healAmount = healAmount;
	}

	@Override
	public void play(Entity target, Calculator cal) {
		target.heal(healAmount);
		cal.addHeal(target, healAmount);
	}

	@Override
	public String getName() {
		return "Heal Card";
	}

	@Override
	public String getDescription() {
		return "Heal " + this.healAmount + " HP.";
	}
}
