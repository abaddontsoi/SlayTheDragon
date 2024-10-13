package card.attack;

import card.ICard;
import entity.Entity;

public abstract class AttackCard implements ICard {
	private double damage;

	public AttackCard(double damage) {
		this.damage = damage;
	}

	@Override
	public void use(Entity user, Entity target) {
		target.takeDamage(damage);
	}

	@Override
	public abstract String getName();

    @Override
	public abstract String getDescription();

}
