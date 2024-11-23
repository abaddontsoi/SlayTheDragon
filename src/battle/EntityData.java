package battle;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import entity.Entity;

public abstract class EntityData {
//	Basic info
	private Entity entity;
	private int totalHeal;
	private int BasicDefense;
	private int BasicStrength;
	private int attackDamage;
	private double AttackBuff;
	private int defense;
	private double defenseBuff;
	private int poisonToEntity;
	private List<ICard> effectsList;

	public EntityData(Entity entity) {
    	this.entity = entity;
        this.BasicDefense = 0;
        this.BasicStrength = 0;
        this.attackDamage = 0;
        this.AttackBuff = 1;
        this.totalHeal = 0;
        this.defense = 0;
        this.defenseBuff = 1;
        this.poisonToEntity = 0;
        this.effectsList = new ArrayList<>();
	}
	
	public void addPoison(int value) {
		this.poisonToEntity += value;
	}
	public int getPoison() {
		return this.poisonToEntity;
	}
	public String getEntityName() {
		return entity.getName();
	}
	
//	Method overload for take damage
	public void takeDamage(double value) {
		this.entity.takeDamage(value);
	}
	public void takeDamage(int value) {
		this.entity.takeDamage(value);
	}
	
	public void reducePoison() {
		this.poisonToEntity -= 1;
	}
	
	public void setAttackBuff(double value) {
		this.AttackBuff = value;
	}
	
	public void addTotalHeal(int value) {
		this.totalHeal += value;
	}
	
	public void setDefenseBuff(double value) {
		this.defenseBuff = value;
	}
	
	public void addAttackDamage(double value) {
		this.attackDamage += value;
	}
	
	public int getBasicStrength() {
		return this.BasicStrength;
	}
	
	public double getAttackBuff() {
		return this.AttackBuff;
	}
	
	public void addDefense(double value) {
		this.defense += value;
	}
	
	public int getBasicDefense() {
		return this.BasicDefense;
	}
	
	public double getDefenseBuff() {
		return this.defenseBuff;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public int getAttackDamage() {
		return this.attackDamage;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public void setAttackDamage(int value) {
		this.attackDamage = value;
	}
	
	public void addDefense(int value) {
		this.defense += value;
	}
	
	public int getHealth() {
		return (int) this.entity.getHealth();
	}
	
	public void setDefense(int value) {
		this.defense = value;
	}
	
	public void addAttackDamage(int value) {
		this.attackDamage += value;
	}
	
	public void reset() {
        this.BasicDefense = 0;
        this.BasicStrength = 0;
        this.attackDamage = 0;
        this.AttackBuff = 1;
        this.totalHeal = 0;
        this.defense = 0;
        this.defenseBuff = 1;
        this.poisonToEntity = 0;
	}
}
