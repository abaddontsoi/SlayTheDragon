package effect;

import entity.Entity;

public class ExtraDefenseEffect extends EffectInTurns {
	private double extraDefense;

	public ExtraDefenseEffect(double extraDefense, int duration) {
		super("Extra Max Health", duration);
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
	public String getFormattedEffectInfo() {
		return String.format("%s (%d rounds, %.2f defense)", name, roundsLeft, extraDefense);
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
}
