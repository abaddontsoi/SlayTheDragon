package card.skill;

import entity.Entity;

abstract public class Defensebuff extends SkillCard {
	private double mag_defense;

	public Defensebuff(double mag_defense) {
		this.mag_defense = mag_defense;
	}

	@Override
	public void play(Entity caster, Entity target) {
		caster.defensebuff(1.5);
	}

}
