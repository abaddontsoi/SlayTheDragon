package card.skill;

public class AdvancedPoisonCard extends PoisonCard{
    public AdvancedPoisonCard() {
		super(10);
	}
	
	@Override
	public String getName() {
		return "Advance Poison Card";
	}
	
    public int getDamage() {
		return poisonDamage;
	}
	@Override
	public String getDescription() {
		return "Deal " + getDamage() + " damage.";
	}
}
