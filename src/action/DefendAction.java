package action;

import entity.Entity;

public class DefendAction implements IAction{
	private double defenseIncrease;

	public DefendAction(double defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    @Override
    public void execute(Entity target) {
        target.increaseDefense(defenseIncrease);
    }

}
