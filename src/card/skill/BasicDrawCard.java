package card.skill;

public class BasicDrawCard extends DrawCard{
    public BasicDrawCard() {
		super(2);
	}

	@Override
	public String getName() {
		return "Basic Draw Card";
	}

	@Override
	public String getDescription() {
		return "Drawn " + this.noDrawCard + " cards.";
	}
}
