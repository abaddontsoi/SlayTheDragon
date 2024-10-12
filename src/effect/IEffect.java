package effect;

import entity.Entity;

public interface IEffect {
	boolean isExpired();
	void apply(Entity target);
	void remove(Entity target);
	void stack(IEffect other);
	void decrementDuration();
	int getRoundsLeft();
	String getName();
}
