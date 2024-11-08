package card.skill;

public class BasicHealCard extends HealCard {

	public BasicHealCard() {
		super(5);
	}

	@Override
	public String getName() {
		return "Basic Heal Card";
	}

	@Override
	public String getDescription() {
		return "Heal " + this.healAmount + " HP.";
	}

}
