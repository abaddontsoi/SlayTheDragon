package effect;

import entity.Entity;

public class PermanentExtraDefenseEffect extends AbstractEffect {
	private double extraDefense;

	public PermanentExtraDefenseEffect(double extraDefense) {
		super("Extra Defense (Permanent)", -1, false);
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
	public void stack(IEffect other) {
		// We cannot stack permanent effects
	}
}
