package entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import effect.EffectInTurns;
import gameIO.GameIO;

public class EntityStatus implements IEntityStatus {
	private double maxHealth;
	private double health;
	private double defense;
	private double strength;
	private List<EffectInTurns> effectsInRounds;
	private List<EffectInTurns> permanentEffectsInRounds;

	public EntityStatus(double maxHealth, double defense, double strength) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.defense = defense;
		this.strength = strength;
		this.effectsInRounds = new ArrayList<EffectInTurns>();
		this.permanentEffectsInRounds = new ArrayList<EffectInTurns>();
	}
	
	@Override
	public double getMaxHealth() {
		// TODO Auto-generated method stub
		return this.maxHealth;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return this.health;
	}

	@Override
	public double getDefense() {
		// TODO Auto-generated method stub
		return this.defense;
	}

	@Override
	public double getStrength() {
		// TODO Auto-generated method stub
		return this.strength;
	}

	@Override
	public List<EffectInTurns> getEffectsInRounds() {
		// TODO Auto-generated method stub
		return this.effectsInRounds;
	}

	@Override
	public List<EffectInTurns> getPermanentEffectsInRounds() {
		// TODO Auto-generated method stub
		return this.permanentEffectsInRounds;
	}

	@Override
	public void setMaxHealth(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHealth(double value) {
		// TODO Auto-generated method stub
		this.health = value;
	}

	@Override
	public void setDefense(double value) {
		// TODO Auto-generated method stub
		this.defense = value;
	}

	@Override
	public void setStrength(double value) {
		// TODO Auto-generated method stub
		this.strength = value;
	}

	@Override
	public void setEffectsInRounds(List<EffectInTurns> effects) {
		// TODO Auto-generated method stub
		this.effectsInRounds = effects;
	}

	@Override
	public void setPermanentEffectsInRounds(List<EffectInTurns> effects) {
		// TODO Auto-generated method stub
		this.permanentEffectsInRounds = effects;
	}

	@Override
	public void takeDamage(double damage) {
		// if (defense >= damage) {
		// 	defense -= damage;
		// } else {
		// 	damage -= defense;
		// 	defense = 0;
		// 	health -= damage;
		// }
		health -= damage;
	}

	@Override
	public void heal(double healAmount) {
		// TODO Auto-generated method stub
		this.health += healAmount;
		if (this.health > this.maxHealth) {
			this.health = this.maxHealth;
		}
	}

	@Override
	public void increaseDefense(double defenseAmount) {
		this.defense += defenseAmount;
	}
	
	@Override
	public void increaseMaxHealth(double healthAmount) {
		// not necessary to increase the current health
		
		this.maxHealth += healthAmount;
		
		// always check health <= max health
		if (this.health > this.maxHealth) {
			this.maxHealth = this.health;
		}
	}
	
	@Override 
	public boolean isAlive() {
		return this.health > 0;
	}
	
	@Override
	/**
	 * Add an effect to the entity. If an effect of the same type already exists,
	 * stack the effect.
	 * Note that we do not apply the effect here, we only add it to the entity.
	 * 
	 * @param effect The effect to add
	 */
	public void addEffect(EffectInTurns effect) {
		for (EffectInTurns existingEffect : effectsInRounds) {
            if (existingEffect.getClass().equals(effect.getClass())) {
                existingEffect.stack(effect);
                return;
            }
        }
		effectsInRounds.add(effect);
	}
	
	@Override
	public void addPermanentEffectInRounds(EffectInTurns effect, Entity entity) {
		for (EffectInTurns existingEffect : permanentEffectsInRounds) {
			if (existingEffect.getClass().equals(effect.getClass())) {
				return;
			}
		}
		effect.apply(entity);
		permanentEffectsInRounds.add(effect);
	}
	
	@Override
	public void applyEffects(GameIO gameIO, Entity entity) {
		Iterator<EffectInTurns> iterator = effectsInRounds.iterator();
        while (iterator.hasNext()) {
        	EffectInTurns effect = iterator.next();
            if (effect.isExpired()) {
            	gameIO.displayExpireEffectMessage(entity, effect);
            	// Clean up the effect, the implementation is up to the effect
                effect.remove(entity);
                // Remove the effect from the list
                iterator.remove();
            }
            else {
            	gameIO.displayEffectApplyMessage(entity, effect);
                effect.apply(entity);
                effect.decrementDuration();
            }
        }
	}
	
	public EntityStatus getStatusCopy() {
		EntityStatus copy = new EntityStatus(this.maxHealth, this.defense, this.strength);
		copy.setPermanentEffectsInRounds(permanentEffectsInRounds);
		copy.setEffectsInRounds(effectsInRounds);
		return copy;
	}
}
