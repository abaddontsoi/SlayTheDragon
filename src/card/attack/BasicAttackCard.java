package card.attack;

import entity.Entity;

public class BasicAttackCard extends AttackCard {

	public BasicAttackCard() {
		super(5);
	}
	
	@Override
	public void use(Entity user, Entity target) {
		super.use(user, target);
	}

	@Override
	public String getName() {
		return "Basic Attack Card";
	}

	@Override
	public String getDescription() {
		return "Deal 5 damage to the target.";
	}

}
