package entity;

import java.util.*;

import card.*;
import effect.*;
import gameIO.GameIO;

public abstract class Entity {
//	protected double maxHealth;
//	protected double health;
//	protected double defense;
//	protected double strength;
//	protected List<EffectInTurns> effectsInRounds;
//	protected List<EffectInTurns> permanentEffectsInRounds;
	
	protected EntityStatus status;
	
	protected CardManager cardManager;
	protected GameIO gameIO;
	
	public Entity(double maxHealth, double defense, double strength, List<ICard> deck) {
//		this.maxHealth = maxHealth;
//		this.health = maxHealth;
//		this.defense = defense;
//		this.strength = strength;
//		this.effectsInRounds = new ArrayList<EffectInTurns>();
//		this.permanentEffectsInRounds = new ArrayList<EffectInTurns>();

		this.status = new EntityStatus(maxHealth, defense, strength);
		
		this.cardManager = new CardManager(deck);
		this.gameIO = GameIO.getInstance();
	}
	
	public void takeDamage(double damage) {
		// Consume defence first
		// If damage is greater than defense, the remaining damage is subtracted from health
//		if (defense >= damage) {
//			defense -= damage;
//		} else {
//			damage -= defense;
//			defense = 0;
//			health -= damage;
//		}

		this.status.takeDamage(damage);
	}
	
	public void heal(double healAmount) {
//		this.health += healAmount;
		this.status.heal(healAmount);
	}
	
	public void increaseDefense(double defenseAmount) {
//		this.defense += defenseAmount;
		this.status.increaseDefense(defenseAmount);
	}
	
	public void decreaseDefense(double defenseAmount) {
		this.status.increaseDefense(-defenseAmount);
	}
	

	// Maybe we can return the chosen action and let the Battle class to execute it
	// The opponent is not necessary to be the action's target
	// The target is determined in the action's execute method
	abstract void chooseCard (Entity opponent);
	
	public void increaseMaxHealth(double healthAmount) {
		this.status.increaseMaxHealth(healthAmount);
	}
	
	public void decreaseMaxHealth(double healthAmount) {
		this.increaseMaxHealth(-healthAmount);
	}
	
	public boolean isAlive() {
		return this.status.isAlive();
	}
	
	public List<ICard> getHand() {
        return cardManager.getHand();
    }
	
	public double getHealth() {
		return this.status.getHealth();
	}
	
	public double getMaxHealth() {
		return this.status.getMaxHealth();
	}
	
	public double getDefense() {
		return this.status.getDefense();
	}
	
	public double getStrength() {
		return this.status.getStrength();
	}
	
	public abstract String getName();
	
	/**
	 * Add an effect to the entity. If an effect of the same type already exists,
	 * stack the effect.
	 * Note that we do not apply the effect here, we only add it to the entity.
	 * 
	 * @param effect The effect to add
	 */
	public void addEffect(EffectInTurns effect) {
		this.status.addEffect(effect);
	}
	
	public void addPermanentEffectInRounds(EffectInTurns effect) {
		this.status.addPermanentEffectInRounds(effect, this);
	}
	
	
	public void applyEffects() {
		this.status.applyEffects(gameIO, this);
	}

	public List<EffectInTurns> getEffects() {
		return this.status.getEffectsInRounds();
	}

	public boolean hasPermanentEffect(EffectInTurns effect) {
		// return permanentEffectsInRounds.contains(effect);
		return this.status.getPermanentEffectsInRounds().contains(effect);
	}

	public EntityStatus getEntityStatus() {
		return this.status.getStatusCopy();
	}
}
