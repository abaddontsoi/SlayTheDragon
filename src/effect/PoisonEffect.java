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
	}
	
	@Override
	public void stack(IEffect other) {
		if (this.equals(other)) {
			this.roundsLeft += other.getRoundsLeft();
		}
	}

}
