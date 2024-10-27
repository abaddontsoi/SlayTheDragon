package effect;

import entity.Entity;

public class PoisonEffect extends EffectInTurns {
	private double poisonDamage;
	

	public PoisonEffect(double poisonDamage, int duration) {
		super("Poison", duration);
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
	public void stack(EffectInTurns other) {
		if (this.equals(other)) {
			// If the other effect is also a poison effect
			// and it has more rounds left than this effect
			// then we should set the rounds left of this effect
			// to be the same as the other effect
			int otherRoundsLeft = other.getRoundsLeft();
			if (otherRoundsLeft > roundsLeft) {
				roundsLeft = otherRoundsLeft;
			}
		}
	}

	@Override
	public String getFormattedEffectInfo() {
		// Show the effect name and the number of rounds and the damage
		return String.format("%s (%d rounds, %.2f damage)", name, roundsLeft, poisonDamage);
	}

}
