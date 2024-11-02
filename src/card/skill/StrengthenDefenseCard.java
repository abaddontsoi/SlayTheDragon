package card.skill;

import entity.Entity;

public class StrengthenDefenseCard implements ICard {
	private double defenseIncrease;

	public StrengthenDefenseCard(double damage) {
		this.defenseIncrease = defenseIncrease;
	}

	@Override
	public void use(Entity user, Entity target) {
		
	}

	@Override
	public String getName() {
		return "Strengthen Defense Card";
	}

}
