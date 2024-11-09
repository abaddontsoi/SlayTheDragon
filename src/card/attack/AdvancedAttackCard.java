package card.attack;

public class AdvancedAttackCard extends AttackCard {

	public AdvancedAttackCard() {
		super(10);
	}
	
	@Override
	public String getName() {
		return "Advance Attack Card";
	}
	
	@Override
	public String getDescription() {
		return "Deal " + getDamage() + " damage.";
	}
}
