package effect;

import entity.Entity;

public class PermanentExtraDefenseEffect extends AbstractEffect {
	private double extraDefense;

	public PermanentExtraDefenseEffect(double extraDefense) {
		super("Extra Defense (Permanent)", -1, true);
		this.extraDefense = extraDefense;
	}
	
	@Override
	public void apply(Entity target) {
		// If the target already has the effect, we should not apply it again
		if (!target.hasEffect(this)) {
			target.increaseDefense(extraDefense);
		}
	}

	@Override
	public void remove(Entity target) {
		target.decreaseDefense(extraDefense);
	}
	
	@Override
	public void stack(IEffect other) {
		// We cannot stack permanent effects
	}
	
	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%.2f defense)", name, extraDefense);
	}
}
