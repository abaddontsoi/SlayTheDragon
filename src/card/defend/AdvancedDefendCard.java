package card.defend;

public class AdvancedDefendCard extends DefendCard {

	public AdvancedDefendCard() {
		super(8);
	}
	
	@Override
	public String getName() {
		return "Advance Defend Card";
	}
	
	@Override
	public String getDescription() {
		return "Gain " + getBlock() + " block.";
	}
}
