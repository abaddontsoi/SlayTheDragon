package card.skill;

public class AdvancedDrawCard extends DrawCard{
    public AdvancedDrawCard() {
		super(3);
	}

	@Override
	public String getName() {
		return "Advanced Draw Card";
	}

	@Override
	public String getDescription() {
		return "Drawn " + this.noDrawCard + " cards.";
	}
}
