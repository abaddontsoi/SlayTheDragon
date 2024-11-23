package card.skill;

public class BasicPoisonCard extends PoisonCard{
    public BasicPoisonCard() {
		super(5);
	}
	
	@Override
	public String getName() {
		return "Basic Poison Card";
	}
	
    public int getDamage() {
		return poisonDamage;
	}
	@Override
	public String getDescription() {
		return "Deal " + getDamage() + " damage.";
	}
}
