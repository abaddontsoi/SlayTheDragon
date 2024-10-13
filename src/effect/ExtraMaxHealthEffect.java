package effect;

import entity.Entity;

public class ExtraMaxHealthEffect extends AbstractEffect {
	private double extraMaxHealth;

	public ExtraMaxHealthEffect(double extraMaxHealth, int duration) {
		super("Extra Max Health", duration, false);
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
        if (this.equals(other)) {
            this.roundsLeft += other.getRoundsLeft();
        }
    }

}
