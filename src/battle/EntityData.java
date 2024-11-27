package battle;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import entity.Entity;

public abstract class EntityData {
	//	Basic info
	private Entity entity;
	private int BasicDefense;
	private int BasicStrength;	
	private int attackDamage;
	private double attackBuff;
	private int defense;
	private double defenseBuff;
	private int poisonToEntity;

	// Accumulated data in battle
	private int receivedPoison;
	private int receivedDamage;
	private int totalCardsPlayed;
	private int totalHeal;
	private int numberOfRounds;

	public EntityData(Entity entity) {
    	this.entity = entity;
        this.BasicDefense = (int)entity.getDefense();
        this.BasicStrength = (int)entity.getStrength();
        this.attackDamage = 0;
        this.attackBuff = 1;
        this.defense = 0;
        this.defenseBuff = 1;
        this.poisonToEntity = 0;
        this.receivedPoison = 0; // # of poison stacks received in the battle 
        this.receivedDamage = 0;
        this.totalHeal = 0;
        this.totalCardsPlayed = 0;
        this.numberOfRounds = 0;
	}
	
	// Poison
	public void addPoison(int value) {
		this.poisonToEntity += value;
		this.receivedPoison += value;
	}
	
	public int getPoison() {
		return this.poisonToEntity;
	}
	
	public void reducePoison() {
		this.poisonToEntity -= 1;
	}
	
	public int getReceivedPoison() {
		return this.receivedPoison;
	}

	// Entity info
	public String getEntityName() {
		return entity.getName();
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	// Received damage
	public int getReceivedDamage() {
		return this.receivedDamage;
	}
	
	public void addReceivedDamage(int value) {
		this.receivedDamage += value;
	}

	//	Method overload for take damage
	public void takeDamage(double value) {
		this.addReceivedDamage((int) value);
		this.entity.takeDamage(value);
	}
	public void takeDamage(int value) {
		this.addReceivedDamage(value);
		this.entity.takeDamage(value);
	}
	
	// # of played cards
	public void addTotalCardsPlayed() {
		this.totalCardsPlayed++;
	}
	
	public int getTotalCardsPlayed() {
		return this.totalCardsPlayed;
	}

	// Healing
	public void addTotalHeal(int value) {
		this.totalHeal += value;
	}
	
	public int getTotalHeal() {
		return this.totalHeal;
	}
	
	public void setDefenseBuff(double value) {
		this.defenseBuff = value;
	}
	
	// Attack
	public void addAttackDamage(double value) {
		this.attackDamage += value;
	}

	public int getAttackDamage() {
		return this.attackDamage;
	}

	public void setAttackDamage(int value) {
		this.attackDamage = value;
	}
	
	public void addAttackDamage(int value) {
		this.attackDamage += value;
	}

	public void setAttackBuff(double value) {
		this.attackBuff = value;
	}
	
	public double getAttackBuff() {
		return this.attackBuff;
	}

	// Strength
	public int getBasicStrength() {
		return this.BasicStrength;
	}
	
	// Defense
	public void addDefense(double value) {
		this.defense += value;
	}

	public int getDefense() {
		return this.defense;
	}
	
	public int getBasicDefense() {
		return this.BasicDefense;
	}
	
	public double getDefenseBuff() {
		return this.defenseBuff;
	}

	public void addDefense(int value) {
		this.defense += value;
	}

	public void setDefense(int value) {
		this.defense = value;
	}
	
	// Health
	public int getHealth() {
		return (int) this.entity.getHealth() <= 0 ? 0 : (int) this.entity.getHealth();
	}
	
	// Rounds
	public int getRounds() {
		return this.numberOfRounds;
	}
	
	public void doneRound() {
		this.numberOfRounds++;
	}
	
	public void reset() {
        this.BasicDefense = 0;
        this.BasicStrength = 0;
        this.attackDamage = 0;
        this.attackBuff = 1;
        this.defense = 0;
        this.defenseBuff = 1;
        this.poisonToEntity = 0;

        this.receivedPoison = 0; // # of poison stacks received in the battle 
        this.receivedDamage = 0;
        this.totalHeal = 0;
        this.totalCardsPlayed = 0;
        this.numberOfRounds = 0;
	}

	public void resetRoundData() {
        this.attackDamage = 0;
        this.attackBuff = 1;
        this.defense = 0;
        this.defenseBuff = 1;
	}
}
