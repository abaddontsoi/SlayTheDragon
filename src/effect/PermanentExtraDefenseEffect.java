package effect;

import entity.Entity;

public class PermanentExtraDefenseEffect extends EffectInTurns {
	private double extraDefense;

	public PermanentExtraDefenseEffect(double extraDefense) {
		super("Extra Defense (Permanent)", -1);
		this.extraDefense = extraDefense;
	}
	
	@Override
	public void apply(Entity target) {
		target.increaseDefense(extraDefense);
	}

	@Override
	public void remove(Entity target) {
		target.decreaseDefense(extraDefense);
	}
	
	@Override
	public void stack(EffectInTurns other) {
		// We cannot stack permanent effects
	}
	
	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%.2f defense)", name, extraDefense);
	}
}
