package card.skill;

import effect.PoisonEffect;
import entity.Entity;

public class BasicPoisonCard extends SkillCard {
	private PoisonEffect effect;

	public BasicPoisonCard() {
		super();
		effect = new PoisonEffect(5, 3);
	}
	
	@Override
	public void use(Entity user, Entity target) {
		target.addEffect(effect);
	}

	@Override
	public String getName() {
		return "Basic Poison Card";
	}

	@Override
	public String getDescription() {
		return "Inflict a poison effect on the target.";
	}


}
