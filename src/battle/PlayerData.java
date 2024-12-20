package battle;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import entity.Entity;

public class PlayerData extends EntityData {
		
//	Additional info for Player
	private int playerTotalDefense;
	private int playerTotalAttackDamage;
	private int playerMaxDamage;
	private int playerMaxDefense;

	private List<ICard> rewards;
	
	public PlayerData(Entity player) {
		super(player);
		
        this.playerMaxDefense = 0;
        this.playerMaxDamage = 0;
        this.playerTotalDefense = 0;
        this.playerTotalAttackDamage = 0;
        this.rewards = new ArrayList<>();
	}
	
	public void addReward(ICard card) {
		this.rewards.add(card);
	}
	
	public List<ICard> getRewards() {
		return this.rewards;
	}
	
	public void addTotalAttackDamage(int value) {
		if (Integer.MAX_VALUE - this.playerTotalAttackDamage < value) {
			this.playerTotalAttackDamage = Integer.MAX_VALUE;
		} else {			
			this.playerTotalAttackDamage += value;
		}
	}
	
	public int getTotalAttackDamage() {
		return this.playerTotalAttackDamage;
	}
	
	public void addTotalDefense(int value) {
		if (Integer.MAX_VALUE - this.playerTotalDefense < value) {
			this.playerTotalDefense = Integer.MAX_VALUE;
		} else {			
			this.playerTotalDefense += value;
		}
	}
	
	public int getTotalDefense() {
		return this.playerTotalDefense;
	}
	
	public int getMaxDamage() {
		return this.playerMaxDamage;
	}
	
	public void updateMaxDamage(int value) {
		this.playerMaxDamage = Math.max(playerMaxDamage, value);
	}
	
	public void updateMaxDefense(int value) {
		this.playerMaxDefense = Math.max(playerMaxDefense, value);
	}
	
	public int getMaxDefense() {
		return this.playerMaxDefense;
	}
}