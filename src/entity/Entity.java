package entity;

import java.util.*;

import card.*;
import effect.*;
import gameIO.GameIO;

public abstract class Entity {
	protected double maxHealth;
	protected double health;
	protected double defense;
	protected double strength;
	protected List<IEffect> effects;
	protected CardManager cardManager;
	protected GameIO gameIO;
	
	public Entity(double maxHealth, double defense, double strength, List<ICard> deck) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.defense = defense;
		this.strength = strength;
		this.effects = new ArrayList<IEffect>();
		this.cardManager = new CardManager(deck);
		this.gameIO = GameIO.getInstance();
	}
	
	public void takeDamage(double damage) {
		// Consume defence first
		// If damage is greater than defense, the remaining damage is subtracted from health
		if (defense >= damage) {
			defense -= damage;
		} else {
			damage -= defense;
			defense = 0;
			health -= damage;
		}
    }
	
	public void heal(double healAmount) {
		this.health += healAmount;
	}
	
	public void increaseDefense(double defenseAmount) {
		this.defense += defenseAmount;
	}
	
	public void decreaseDefense(double defenseAmount) {
		this.defense -= defenseAmount;
	}
	

	// Maybe we can return the chosen action and let the Battle class to execute it
	// The opponent is not necessary to be the action's target
	// The target is determined in the action's execute method
	abstract void chooseCard (Entity opponent);
	
	public void increaseMaxHealth(double healthAmount) {
		// not necessary to increase the current health
		this.maxHealth += healthAmount;
	}
	
	public void decreaseMaxHealth(double healthAmount) {
		this.maxHealth -= healthAmount;
		if (health > maxHealth) {
			health = maxHealth;
		}
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public List<ICard> getHand() {
        return cardManager.getHand();
    }
	
	public double getHealth() {
		return health;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}
	
	public double getDefense() {
		return defense;
	}
	
	public double getStrength() {
		return strength;
	}
	
	public abstract String getName();
	
	/**
	 * Add an effect to the entity. If an effect of the same type already exists,
	 * stack the effect.
	 * Note that we do not apply the effect here, we only add it to the entity.
	 * 
	 * @param effect The effect to add
	 */
	public void addEffect(IEffect effect) {
		for (IEffect existingEffect : effects) {
            if (existingEffect.getClass().equals(effect.getClass())) {
                existingEffect.stack(effect);
                return;
            }
        }
        effects.add(effect);
	}
	
	public void applyEffects() {
		Iterator<IEffect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            IEffect effect = iterator.next();
            if (effect.isExpired()) {
            	this.gameIO.displayExpireEffectMessage(this, effect);
            	// Clean up the effect, the implementation is up to the effect
                effect.remove(this);
                // Remove the effect from the list
                iterator.remove();
            }
            else {
            	this.gameIO.displayEffectApplyMessage(this, effect);
                effect.apply(this);
                effect.decrementDuration();
            }
            
        }
	}

	public List<IEffect> getEffects() {
        return effects;
	}

	public boolean hasEffect(IEffect effect) {
        for (IEffect existingEffect : effects) {
            if (existingEffect.getClass().equals(effect.getClass())) {
                return true;
            }
        }
        return false;
    }

}
