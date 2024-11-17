package card.attack;

public class BasicAttackCard extends AttackCard {

	public BasicAttackCard() {
		super(1000);
	}
	
	@Override
	public String getName() {
		return "Basic Attack Card";
	}
	
	@Override
	public String getDescription() {
		return "Deal " + getDamage() + " damage.";
	}
}
