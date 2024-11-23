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

public class Calculator {
	private GameIO gameIO;

	private PlayerData playerData;
	private FoeData foeData;


	public Calculator(Entity player, Entity foe) {
    	this.gameIO = GameIO.getInstance();
    	this.playerData = new PlayerData(player);
    	this.foeData = new FoeData(foe);
    }

    public void setPoisonDamage(Entity target, int poisonDamage) {
        if (target instanceof Player) {
            this.foeData.addPoison(poisonDamage);
        } else {
//            playerData.poisonToPlayer += poisonDamage;
        	this.playerData.addPoison(poisonDamage);
        }
    }

    public void applyPoisonEffect() {
        if (foeData.getPoison() > 0) {
            gameIO.displayMessage("Poison damage (" + foeData.getPoison() + ")to " + foeData.getEntityName());
            foeData.takeDamage(foeData.getPoison());
            foeData.reducePoison();
        }
//        if (playerData.poisonToPlayer > 0) {
//            gameIO.displayMessage("Poison damage (" + playerData.poisonToPlayer + ")to Player");
//            playerData.player.takeDamage(playerData.poisonToPlayer);
//            playerData.poisonToPlayer--;
//        }
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
            // gameIO.displayMessage("foe choose");

            // gameIO.displayMessage(card.getName());
            if (card instanceof AttackCard) {
//                foeData.foeAttackDamage += (((AttackCard) card).getDamage() + foeData.foeBasicStrength) * foeData.foeAttackBuff;// buff
            	foeData.addAttackDamage((((AttackCard) card).getDamage() + foeData.getBasicStrength()) * foeData.getAttackBuff());
            }
            if (card instanceof DefendCard) {
//                foeData.foeDefense += (((DefendCard) card).getBlock() + foeData.foeBasicDefense) * foeData.foeDefenseBuff;
            	foeData.addDefense((((DefendCard) card).getBlock() + foeData.getBasicDefense()) * foeData.getDefenseBuff());
            }
            if (card instanceof SkillCard) {
                ((SkillCard) card).play(foeData.getEntity(), this);
            }
        });
        gameIO.displayMessage("Damage To Player: " + foeData.getAttackDamage() + " ,Block: " + foeData.getDefense());
    }

    public void calculatePlayerAction(ICard card) {
        gameIO.displayMessage("player choose: " + card.getName());
        if (card instanceof AttackCard) {
//            playerData.playerAttackDamage = (int) ((((AttackCard) card).getDamage() + playerData.playerBasicStrength) * playerData.playerAttackBuff);
        	playerData.setAttackDamage((int) ((((AttackCard) card).getDamage() + playerData.getBasicStrength()) * playerData.getAttackBuff()));
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
//            playerData.playerDefense += (((DefendCard) card).getBlock() + playerData.playerBasicDefense) * playerData.playerDefenseBuff;
        	playerData.addDefense((((DefendCard) card).getBlock() + playerData.getBasicDefense()) * playerData.getDefenseBuff());
//        	playerData.playerTotalDefense += playerData.playerDefense;
        	playerData.addTotalDefense(playerData.getDefense()); 
        }
        if (card instanceof SkillCard) {
            ((SkillCard) card).play(playerData.getEntity(), this);
        } 
        gameIO.displayMessage("============================");
    }

    public void calculateRound() { // return record
        foeData.addAttackDamage(-playerData.getDefense());
        if(foeData.getAttackDamage() < 0){
//            playerData.playerDefense -= foeData.foeAttackDamage;
//            foeData.foeAttackDamage = 0;
        	playerData.addDefense(foeData.getAttackDamage());
        	foeData.setAttackDamage(0);
        }
        gameIO.displayMessage(foeData.getEntityName() +" Demage To Player: " + foeData.getAttackDamage());
//        playerData.player.takeDamage(foeData.getAttackDamage());
        playerData.takeDamage(foeData.getAttackDamage());
        gameIO.displayMessage(playerData.getEntityName() + " Status: Health: " + playerData.getHealth());


//        if (playerData.playerAttackDamage > playerData.playerMaxDemage) {
//            playerData.playerMaxDemage = playerData.playerAttackDamage;
//        }
        playerData.updateMaxDamage(playerData.getAttackDamage());
        
//        if (playerData.playerDefense > playerData.playerMaxDefense) {
//            playerData.playerMaxDefense = playerData.playerDefense;
//        }
        playerData.updateMaxDefense(playerData.getDefense());
    }


    // public void calculateRound(List<ICard> pCards, List<ICard> eCards) {
    // eCards.forEach((card) -> {
    // gameIO.displayMessage("foe choose");

    // gameIO.displayMessage(card.getName());
    // if(card instanceof AttackCard){
    // foeAttackDamage += (((AttackCard)card).getDamage() + foeBasicStrength) *
    // foeAttackBuff;//buff
    // }
    // if(card instanceof DefendCard){
    // foeDefense += (((DefendCard)card).getBlock() + foeBasicDefense) *
    // foeDefenseBuff;
    // }
    // if(card instanceof SkillCard){
    // ((SkillCard)card).play(foe, this);
    // }
    // });

    // pCards.forEach((card) -> {
    // gameIO.displayMessage("player choose");

    // gameIO.displayMessage(card.getName());

    // if(card instanceof AttackCard){
    // playerAttackDamage += (((AttackCard)card).getDamage() + playerBasicStrength)
    // * playerAttackBuff;
    // }
    // if(card instanceof DefendCard){
    // playerDefense += (((DefendCard)card).getBlock() + playerBasicDefense) *
    // playerDefenseBuff ;
    // }
    // if(card instanceof SkillCard){
    // ((SkillCard)card).play(player, this);
    // }
    // });
    // gameIO.displayMessage("========== Card Stat ===========");
    // gameIO.displayMessage("playerAttackDamage: " + playerAttackDamage);
    // gameIO.displayMessage("playerDefense: " + playerDefense);
    // gameIO.displayMessage("foeAttackDamage: " + foeAttackDamage);
    // gameIO.displayMessage("foeDefense: " + foeDefense);

    // playerAttackDamage -= foeDefense;
    // if(playerAttackDamage < 0) {
    // playerAttackDamage = 0;
    // }

    // foeAttackDamage -= playerDefense;
    // if(foeAttackDamage < 0) {
    // foeAttackDamage = 0;
    // }

    // gameIO.displayMessage("========== Result ===========");

    // gameIO.displayMessage("playerAttackDamage: " + playerAttackDamage);
    // gameIO.displayMessage("playerDefense: " + playerDefense);
    // gameIO.displayMessage("foeAttackDamage: " + foeAttackDamage);
    // gameIO.displayMessage("foeDefense: " + foeDefense);
    // player.takeDamage(foeAttackDamage);
    // foe.takeDamage(playerAttackDamage);

    // // RoundRecord record = new RoundRecord(playerAttackDamage,
    // playerDefense....)
    // if(playerAttackDamage > playerMaxDemage){
    // playerMaxDemage = playerAttackDamage;
    // }
    // if(playerDefense > playerMaxDefense){
    // playerMaxDefense = playerDefense;
    // }
    // }

    public void reset() {
//        playerData.playerAttackDamage = 0;
//        foeData.foeAttackDamage = 0;
//        playerData.playerDefense = 0;
//        foeData.foeDefense = 0;
//        playerData.playerAttackBuff = 1;
//        playerData.playerDefenseBuff = 1;
//        foeData.foeAttackBuff = 1;
//        foeData.foeDefenseBuff = 1;
    	
    	playerData.reset();
    	foeData.reset();
    }
}
