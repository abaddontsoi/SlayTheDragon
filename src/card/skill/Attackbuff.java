package card.skill;

import entity.Entity;

public class Attackbuff extends SkillCard {
	private double mag_damage;

	public Attackbuff(double mag_damage) {
		this.mag_damage = mag_damage;
	}

	@Override
	public void play(Entity caster, Entity target) {
		caster.attackbuff(1.5);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
