package battle;

import java.util.ArrayList;
import java.util.List;

import card.CardManager;
import card.ICard;
import card.attack.*;
import card.defend.*;
import card.skill.*;
import effect.IEffect;
import entity.Entity;
import entity.Foe;
import entity.Player;
import gameIO.GameIO;
import battle.record.*;
import battle.record.Record;

public class ComputeCenter {
	private GameIO gameIO;

	private PlayerData playerData;
	private FoeData foeData;


	public ComputeCenter(Entity player, Entity foe) {
    	this.gameIO = GameIO.getInstance();
    	this.playerData = new PlayerData(player);
    	this.foeData = new FoeData(foe);
    }

	public void finishFoeRound() {
		foeData.doneRound();
	}
	
	public void finishPlayerRound() {
		playerData.doneRound();
	}
	
    public void setPoisonDamage(Entity target, int poisonDamage) {
        if (target instanceof Player) {
            this.foeData.addPoison(poisonDamage);
        } else {
        	this.playerData.addPoison(poisonDamage);
        }
    }

    public void applyPoisonEffect() {
        if (foeData.getPoison() > 0) {
            gameIO.displayMessage("Poison damage (" + foeData.getPoison() + ")to " + foeData.getEntityName());
            foeData.takeDamage(foeData.getPoison());
            foeData.reducePoison();
        }
        if (playerData.getPoison() > 0) {
            gameIO.displayMessage("Poison damage (" + playerData.getPoison() + ")to Plater");
            playerData.takeDamage(playerData.getPoison());
            playerData.reducePoison();
        }
    }

    public void setAttackBuff(Entity target, double attackbuff) {
        if (target instanceof Player) {
        	playerData.setAttackBuff(attackbuff);
        } else {
            foeData.setAttackBuff(attackbuff);
        }
    }

    public void addHeal(Entity target, int healAmount) {
        if (target instanceof Player) {
        	playerData.addTotalHeal(healAmount);
        } else {
            foeData.addTotalHeal(healAmount);
        }
    }

    public void setDefenseBuff(Entity target, double defensebuff) {
        if (target instanceof Player) {
        	playerData.setDefenseBuff(defensebuff);
        } else {
            foeData.setDefenseBuff(defensebuff);
        }
    }

    public void calculateFoeRound(List<ICard> eCards) {
        eCards.forEach((card) -> {
            if (card instanceof AttackCard) {
            	foeData.addAttackDamage((((AttackCard) card).getDamage() + foeData.getBasicStrength()) * foeData.getAttackBuff());
            }
            if (card instanceof DefendCard) {
            	foeData.addDefense((((DefendCard) card).getBlock() + foeData.getBasicDefense()) * foeData.getDefenseBuff());
            }
            if (card instanceof SkillCard) {
                ((SkillCard) card).play(foeData.getEntity(), this);
            }
        });
        this.finishFoeRound();
        gameIO.displayMessage("Damage To Player: " + foeData.getAttackDamage() + " ,Block: " + foeData.getDefense());
    }

    public void calculatePlayerAction(ICard card) {
        gameIO.displayMessage("player choose: " + card.getName());
        playerData.addTotalCardsPlayed();
        
        if (card instanceof AttackCard) {
        	
        	int playerDamage = (int) ((((AttackCard) card).getDamage() + playerData.getBasicStrength()) * playerData.getAttackBuff());
        	playerData.setAttackDamage(playerDamage);
        	playerData.addTotalAttackDamage(playerData.getAttackDamage());
        	
            if (playerData.getAttackDamage() < foeData.getDefense()) {
                foeData.addDefense(-playerData.getAttackDamage());
                
                gameIO.displayMessage("Player Damage To " + foeData.getEntityName() + ": 0");
                gameIO.displayMessage(foeData.getEntityName() + " Status: Health: " + foeData.getEntity().getHealth() + ", Attack: "
                        + foeData.getAttackDamage() + ", Remain Block: " + foeData.getDefense());
            } else {
                gameIO.displayMessage("Player Damage To " + foeData.getEntityName() + ": " + (playerData.getAttackDamage() - foeData.getDefense()));
                
                foeData.takeDamage(playerData.getAttackDamage() - foeData.getDefense());
                foeData.setDefense(0);
                
                gameIO.displayMessage(foeData.getEntityName() + " Status: Health: " + foeData.getHealth() + ", Attack: "
                        + foeData.getAttackDamage() + ", Remain Block: " + foeData.getDefense());

            }
            
        }
        if (card instanceof DefendCard) {
        	playerData.addDefense((((DefendCard) card).getBlock() + playerData.getBasicDefense()) * playerData.getDefenseBuff());
        	playerData.addTotalDefense(playerData.getDefense()); 
        }
        if (card instanceof SkillCard) {
            ((SkillCard) card).play(playerData.getEntity(), this);
        } 
        gameIO.displayMessage("============================");
    }

    public void calculateRound() { // return record
    	gameIO.displayMessage(foeData.getEntityName() +" Demage To Player: " + foeData.getAttackDamage());
    	
    	if (foeData.getAttackDamage() <= playerData.getDefense()) {
    		// Foe attack player
    		playerData.addDefense(-foeData.getAttackDamage());
    	} else {
    		// Foe's attack > player defense
    		// Player have to take damage
    		int playerReceivedDamage = foeData.getAttackDamage() - playerData.getDefense();
    		playerData.setDefense(0);
    		playerData.takeDamage(playerReceivedDamage);
    	}
    	// Attack done, reset its value
		foeData.setAttackDamage(0);
        
		gameIO.displayMessage(playerData.getEntityName() + " Status: Health: " + playerData.getHealth());

        playerData.updateMaxDamage(playerData.getAttackDamage());
        playerData.updateMaxDefense(playerData.getDefense());
    }

	public void addPlayerReward(ICard card) {
		playerData.addReward(card);
	}
    
    public void reset() { 	
    	playerData.reset();
    	foeData.reset();
    }
    
    public void genBattleRecord() {
    	BattleRecord.createRecord(playerData, foeData);
    }
}
