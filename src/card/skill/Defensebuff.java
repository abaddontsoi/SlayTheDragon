package card.skill;

import battle.Calculator;
import entity.Entity;

abstract public class Defensebuff extends SkillCard {
	private double mag_defense;

	public Defensebuff(double mag_defense) {
		this.mag_defense = mag_defense;
	}

	@Override
	public void play(Entity caster, Entity target, Calculator cal) {
		caster.defensebuff(1.5);
	}

	@Override
	public String getName() {
		return "DefenseBuff Card";
	}

	@Override
	public String getDescription() {
		return "Defense buffed" + this.mag_defense + " times.";
	}
}
