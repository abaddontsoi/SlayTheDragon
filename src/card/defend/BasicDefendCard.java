package card.defend;

import entity.Entity;

public class BasicDefendCard extends DefendCard {

	public BasicDefendCard() {
		super(5);
	}
	
	@Override
	public void use(Entity user, Entity target) {
		super.use(user, target);
	}
	
	@Override
	public String getName() {
        return "Basic Defend Card";
	}
	
	@Override
	public String getDescription() {
		return "Gain 5 block.";
	}
}
