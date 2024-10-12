package action;

import entity.Entity;

public class AttackAction implements IAction {
	private double damage;

	public AttackAction(double damage) {
	    this.damage = damage;
	}

	@Override
	public void execute(Entity target) {
		target.takeDamage(damage);	
	}

	
}
