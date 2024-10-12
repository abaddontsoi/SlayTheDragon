package effect;

import entity.Entity;

public abstract class AbstractEffect implements IEffect{
	protected String name;
    protected int roundsLeft;
    protected boolean isPermanent;

	public AbstractEffect(String name, int roundsLeft, boolean isPermanent) {
		this.name = name;
		this.roundsLeft = roundsLeft;
		this.isPermanent = isPermanent;
	}
	
	@Override
	public boolean isExpired() {
		return !isPermanent && roundsLeft <= 0;
	}
	
	@Override
	public abstract void apply(Entity target);
	
	@Override
	public abstract void remove(Entity target);
	
	@Override
	public void decrementDuration() {
		if (!isPermanent) {
			roundsLeft--;
		}
	}
	
	@Override
	public abstract void stack(IEffect other);
	
	@Override
	public int getRoundsLeft() {
		return roundsLeft;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractEffect) {
			AbstractEffect other = (AbstractEffect) obj;
			return 
					this.getClass().equals(other.getClass()) 
					&& this.name.equals(other.name);
		}
		return false;
	}
}
