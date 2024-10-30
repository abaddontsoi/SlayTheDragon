package entity;

import java.util.List;

import effect.EffectInTurns;
import gameIO.GameIO;

public interface IEntityStatus {
	double getMaxHealth();
	double getHealth();
	double getDefense();
	double getStrength();
	
	void setMaxHealth(double value);
	void setHealth(double value);
	void setDefense(double value);
	void setStrength(double value);
	
	List<EffectInTurns> getEffectsInRounds();
	List<EffectInTurns> getPermanentEffectsInRounds();

	void setEffectsInRounds(List<EffectInTurns> effects);
	void setPermanentEffectsInRounds(List<EffectInTurns> effects);
	
//	functional for battle
	void takeDamage(double damage);
	void heal(double healAmount);
	void increaseDefense(double defenseAmount);
	void increaseMaxHealth(double healthAmount);
	boolean isAlive();
	void addEffect(EffectInTurns effect);
	void addPermanentEffectInRounds(EffectInTurns effect, Entity entity);
	void applyEffects(GameIO gameIO, Entity entity);
}
