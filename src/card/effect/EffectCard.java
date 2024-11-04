package card.effect;

import card.ICard;
import effect.IEffect;
import entity.Entity;

public abstract class EffectCard implements ICard {
	IEffect effectCard;
	public EffectCard(IEffect IEffect) {
		this.effectCard = IEffect;
	}

	@Override
	public abstract void use(Entity user, Entity target);

	@Override
	public abstract String getName();

    @Override
	public abstract String getDescription();
}
