package effect;

import entity.Entity;

public abstract class EffectInTurns implements IEffect {
	protected String name;
	protected int roundsLeft;

	public EffectInTurns(String name, int roundsLeft) {
		this.name = name;
		this.roundsLeft = roundsLeft;
	}

	public boolean isExpired() {
        return roundsLeft <= 0;
	}
	
	public int getRoundsLeft() {
		return roundsLeft;
	}
	
	public void decrementDuration() {
		roundsLeft--;
	}

	
	public abstract void apply(Entity target);

	public abstract void remove(Entity target);
	
	public abstract void stack(EffectInTurns other);
	
	@Override
	public abstract String getFormattedEffectInfo();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EffectInTurns) {
			EffectInTurns other = (EffectInTurns) obj;
			return this.getClass().equals(other.getClass()) && this.name.equals(other.name);
		}
		return false;
	}
}
