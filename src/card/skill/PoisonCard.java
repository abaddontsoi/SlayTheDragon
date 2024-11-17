package card.skill;
import battle.*;
import entity.Entity;

public abstract class PoisonCard extends SkillCard{
    protected int poisonDamage;

    public PoisonCard(int poisonDamage) {
        this.poisonDamage = poisonDamage;
	}

    @Override
	public void play(Entity caster, Entity target, Calculator cal) {
		
	}

    @Override
	public String getName() {
		return "Poison Card";
	}

	@Override
	public String getDescription() {
		return "Poison " + this.poisonDamage + " damage.";
	}
}
