package card.attack;

import card.ICard;
import entity.Entity;

public class AttackCard implements ICard {
	private double damage;

	public AttackCard(double damage) {
		this.damage = damage;
	}

	@Override
	public void use(Entity user, Entity target) {
		target.takeDamage(damage);
	}

	@Override
	public String getName(){
		return null;
	}

    @Override
	public String getDescription(){
		return null;
	}

}
