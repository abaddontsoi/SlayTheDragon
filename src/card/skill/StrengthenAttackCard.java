package card.skill;

import entity.Entity;

public class StrengthenAttackCard extends SkillCard {
	private double mag_damage;

	public StrengthenAttackCard(double mag_damage) {
		this.mag_damage = mag_damage;
	}

	@Override
	public void enhance() {
		
	}

	@Override
	public String getName() {
		return "Strengthen Attack Card";
	}

}
