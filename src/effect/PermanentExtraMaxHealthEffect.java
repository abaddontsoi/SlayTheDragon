package effect;

import entity.Entity;

public class PermanentExtraMaxHealthEffect extends AbstractEffect {
	private double extraMaxHealth;

	public PermanentExtraMaxHealthEffect(double extraMaxHealth) {
		super("Extra Max Health (Permanent)", -1, false);
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
	public void stack(IEffect other) {
		// We cannot stack permanent effects
	}

}
