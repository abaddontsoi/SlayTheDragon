package effect;

import entity.Entity;

public class ExtraMaxHealthEffect extends EffectInTurns {
	private double extraMaxHealth;

	public ExtraMaxHealthEffect(double extraMaxHealth, int duration) {
		super("Extra Max Health", duration);
		this.extraMaxHealth = extraMaxHealth;
	}
	
	@Override
	public void apply(Entity target) {
		target.increaseMaxHealth(extraMaxHealth);
	}

	@Override
	public void remove(Entity target) {
		target.decreaseMaxHealth(extraMaxHealth);
	}
	
	@Override
	public void stack(EffectInTurns other) {
		if (this.equals(other)) {
			int otherRoundsLeft = other.getRoundsLeft();
			if (otherRoundsLeft > roundsLeft) {
				roundsLeft = otherRoundsLeft;
			}
		}
	}
	
	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%d rounds, %.2f max health)", name, roundsLeft, extraMaxHealth);
	}

}
