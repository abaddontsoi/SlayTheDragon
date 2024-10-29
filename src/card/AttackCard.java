package card;

//import action.AttackAction;
import entity.Entity;

public class AttackCard implements ICard {
	private double damage;

	public AttackCard(double damage) {
		this.damage = damage;
	}

	@Override
	public void use(Entity user, Entity target) {
//		new AttackAction(damage).execute(target);
	}

	@Override
	public String getName() {
		return "Attack Card";
	}

	@Override
	public String getDescription() {
		return "";
	}
}
