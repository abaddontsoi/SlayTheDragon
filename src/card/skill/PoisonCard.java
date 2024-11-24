package card.skill;
import battle.*;
import entity.Entity;
import entity.Player;

public abstract class PoisonCard extends SkillCard{
    protected int poisonDamage;

    public PoisonCard(int poisonDamage) {
        this.poisonDamage = poisonDamage;
	}

    @Override
	public void play(Entity target, ComputeCenter cal) {
		cal.setPoisonDamage(target, poisonDamage);
	}

}
