package entity;

import java.util.*;

import action.IAction;
import card.*;
import effect.*;

public abstract class Entity {
	protected double maxHealth;
	protected double health;
	protected double defense;
	protected double strength;
	protected List<IEffect> effects;
	protected List<ICard> hand;
	
	public Entity(double maxHealth, double defense, double strength) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.defense = defense;
		this.strength = strength;
		this.effects = new ArrayList<IEffect>();
		this.hand = new ArrayList<ICard>();
	}
	
	public void takeDamage(double damage) {
		double actualDamage = Math.max(0, damage - defense);
		this.health -= actualDamage;
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
	
	public void drawCard() {
		// Not implemented yet
	}
	
	// Maybe we can return the chosen action and let the Battle class to execute it
	// The opponent is not necessary to be the action's target
	// The target is determined in the action's execute method
	abstract void chooseAction(Entity opponent);
	
	public void increaseMaxHealth(double healthAmount) {
		this.maxHealth += healthAmount;
	}
	
	public void decreaseMaxHealth(double healthAmount) {
		this.maxHealth -= healthAmount;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public List<ICard> getHand() {
        return hand;
    }
	
	public void addEffect(IEffect effect) {
		for (IEffect existingEffect : effects) {
            if (existingEffect.getClass().equals(effect.getClass())) {
                existingEffect.stack(effect);
                return;
            }
        }
        effects.add(effect);
        effect.apply(this);
	}
	
	public void applyEffects() {
		Iterator<IEffect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            IEffect effect = iterator.next();
            effect.apply(this);
            effect.decrementDuration();
            if (effect.isExpired()) {
            	// Clean up the effect, the implementation is up to the effect
                effect.remove(this);
                // Remove the effect from the list
                iterator.remove();
            }
        }
	}
}
