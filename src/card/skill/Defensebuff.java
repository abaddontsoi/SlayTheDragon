package card.skill;

import battle.Calculator;
import entity.Entity;

abstract public class Defensebuff extends SkillCard {
	private double mag_defense;

	public Defensebuff(double mag_defense) {
		this.mag_defense = mag_defense;
	}

	@Override
	public void play(Entity target, Calculator cal) {
		cal.setDefenseBuff(target, mag_defense);
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
