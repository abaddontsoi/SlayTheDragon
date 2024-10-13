package effect;

import entity.Entity;

public class ExtraDefenseEffect extends AbstractEffect {
	private double extraDefense;

	public ExtraDefenseEffect(double extraDefense, int duration) {
		super("Extra Max Health", duration, false);
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
		if (this.equals(other)) {
			this.roundsLeft += other.getRoundsLeft();
		}
	}

	@Override
	public String getFormattedEffectInfo() {
		return String.format("%s (%d rounds, %.2f defense)", name, roundsLeft, extraDefense);
	}

}
