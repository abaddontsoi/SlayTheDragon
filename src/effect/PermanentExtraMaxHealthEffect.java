package effect;

import entity.Entity;

public class PermanentExtraMaxHealthEffect extends AbstractEffect {
	private double extraMaxHealth;

	public PermanentExtraMaxHealthEffect(double extraMaxHealth) {
		super("Extra Max Health (Permanent)", -1, true);
		this.extraMaxHealth = extraMaxHealth;
	}

	@Override
	public void apply(Entity target) {
		// If the target already has the effect, we should not apply it again
		if (!target.hasEffect(this)) {
            target.increaseMaxHealth(extraMaxHealth);
            target.heal(extraMaxHealth);
		}
	}

	@Override
	public void remove(Entity target) {
		target.decreaseMaxHealth(extraMaxHealth);
	}
	
	@Override
	public void stack(IEffect other) {
		// We cannot stack permanent effects
	}
	
	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%.2f max health)", name, extraMaxHealth);
	}

}
