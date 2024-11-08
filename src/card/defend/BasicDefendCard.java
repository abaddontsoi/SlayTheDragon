package card.defend;

public class BasicDefendCard extends DefendCard {

	public BasicDefendCard() {
		super(5);
	}
	
	@Override
	public String getName() {
		return "Basic Defend Card";
	}
	
	@Override
	public String getDescription() {
		return "Gain " + getBlock() + " block.";
	}
}
