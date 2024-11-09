package card.attack;

import card.ICard;

abstract class AttackCard implements ICard {
	private int damage;

	public AttackCard(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public AttackCard clone() {
		try {
			return (AttackCard) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
