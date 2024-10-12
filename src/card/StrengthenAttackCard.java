package card;

import action.AttackAction;
import entity.Entity;

public class StrengthenAttackCard implements ICard {
	private double damage;

	public StrengthenAttackCard(double damage) {
		this.damage = damage;
	}

	@Override
	public void use(Entity user, Entity target) {
		new AttackAction(damage).execute(target);
	}

	@Override
	public String getName() {
		return "Strengthen Attack Card";
	}

}
