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

	public PlayerData(Entity player) {
		super(player);
		
        this.playerMaxDefense = 0;
        this.playerMaxDamage = 0;
        this.playerTotalDefense = 0;
        this.playerTotalAttackDamage = 0;
	}
	
	public void addTotalAttackDamage(int value) {
		this.playerTotalAttackDamage += value;
	}
	
	public void addTotalDefense(int value) {
		this.playerTotalDefense += value;
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
}