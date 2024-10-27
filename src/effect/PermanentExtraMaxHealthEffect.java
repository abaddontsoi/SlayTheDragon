package effect;

import entity.Entity;

public class PermanentExtraMaxHealthEffect extends EffectInTurns {
	private double extraMaxHealth;

	public PermanentExtraMaxHealthEffect(double extraMaxHealth) {
		super("Extra Max Health (Permanent)", -1);
		this.extraMaxHealth = extraMaxHealth;
	}

	@Override
	public void apply(Entity target) {
		target.increaseMaxHealth(extraMaxHealth);
        target.heal(extraMaxHealth);
	}

	@Override
	public void remove(Entity target) {
		target.decreaseMaxHealth(extraMaxHealth);
	}
	
	@Override
	public void stack(EffectInTurns other) {
		// We cannot stack permanent effects
	}
	
	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%.2f max health)", name, extraMaxHealth);
	}

}
