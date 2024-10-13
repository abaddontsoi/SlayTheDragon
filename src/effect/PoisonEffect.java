package effect;

import entity.Entity;

public class PoisonEffect extends AbstractEffect {
	private double poisonDamage;
	

	public PoisonEffect(double poisonDamage, int duration) {
		super("Poison", duration, false);
		this.poisonDamage = poisonDamage;
	}
	
	@Override
	public void apply(Entity target) {
		target.takeDamage(poisonDamage);
	}

	@Override
	public void remove(Entity target) {
		// Do nothing
		// since poison effect does not modify the target's stats
	}
	
	@Override
	public void stack(IEffect other) {
		if (this.equals(other)) {
			this.roundsLeft += other.getRoundsLeft();
		}
	}

	@Override
	public String getFormattedEffectInfo() {
		// Show the effect name and the number of rounds and the damage
		return String.format("%s (%d rounds, %.2f damage)", name, roundsLeft, poisonDamage);
	}

}
