package card.skill;

import battle.ComputeCenter;
import entity.Entity;

public class Attackbuff extends SkillCard {
	private double mag_damage;

	public Attackbuff(double mag_damage) {
		this.mag_damage = mag_damage;
	}

	@Override
	public void play(Entity target, ComputeCenter cal) {
		cal.setAttackBuff(target, mag_damage);
	}

	@Override
	public String getName() {
		return "AttackBuff Card";
	}

	@Override
	public String getDescription() {
		return "Attack buffed" + this.mag_damage + " times.";
	}

}
