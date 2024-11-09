package card.skill;

public class AdvancedHealCard extends HealCard {

	public AdvancedHealCard() {
		super(10);
	}

	@Override
	public String getName() {
		return "Advance Heal Card";
	}

	@Override
	public String getDescription() {
		return "Heal " + this.healAmount + " HP.";
	}

}
