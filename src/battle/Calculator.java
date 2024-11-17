package battle;

import java.util.ArrayList;
import java.util.List;

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
    private Entity player;
    private Entity foe;
    private GameIO gameIO;

    private int playerMaxDemage;
    private int playerMaxDefense;

    private int playerHeal;
    private int foeHeal;

    private int playerBasicDefense;
    private int playerBasicStrength;

    private double foeBasicDefense;
    private double foeBasicStrength;

    private int playerAttackDamage;
    private int playerTotalAttackDamage;
    private int foeAttackDamage;
    private double playerAttackBuff;
    private double foeAttackBuff;

    private int playerDefense;
    private int playerTotalDefense;
    private int foeDefense;

    private int poisonToPlayer;
    private int poisonToFoe;

    private double playerDefenseBuff;
    private double foeDefenseBuff;

    private List<ICard> playerEffectsList; // hashmap <effect, duration>
    private List<ICard> foeEffectsList;

    public Calculator(Entity player, Entity foe) {
        this.player = player;
        this.foe = foe;
        this.gameIO = GameIO.getInstance();
        this.playerBasicDefense = 0;
        this.playerBasicStrength = 0;

        this.foeBasicDefense = foe.getDefense();
        this.foeBasicStrength = foe.getDefense();
        this.playerMaxDemage = 0;
        this.playerMaxDefense = 0;
        this.playerHeal = 0;
        this.foeHeal = 0;
        this.playerAttackDamage = 0;
        this.playerTotalAttackDamage = 0;
        this.foeAttackDamage = 0;
        this.playerAttackBuff = 1;
        this.foeAttackBuff = 1;

        this.playerDefense = 0;
        this.playerTotalDefense = 0;
        this.foeDefense = 0;
        this.playerDefenseBuff = 1;
        this.foeDefenseBuff = 1;
        this.poisonToPlayer = 0;
        this.poisonToFoe = 0;

        this.playerEffectsList = new ArrayList<>();
        this.foeEffectsList = new ArrayList<>();
    }

    public void setPoisonDamage(Entity target, int poisonDamage) {
        if (target instanceof Player) {
            poisonToFoe += poisonDamage;
        } else {
            poisonToPlayer += poisonDamage;
        }
    }

    public void applyPoisonEffect() {
        if (poisonToFoe > 0) {
            gameIO.displayMessage("Poison damage (" + poisonToFoe + ")to " + foe.getName());
            foe.takeDamage(poisonToFoe);
            poisonToFoe--;
        }
        if (poisonToPlayer > 0) {
            gameIO.displayMessage("Poison damage (" + poisonToPlayer + ")to Player");
            player.takeDamage(poisonToPlayer);
            poisonToPlayer--;
        }
    }

    public void setAttackBuff(Entity target, double attackbuff) {
        if (target instanceof Player) {
            playerAttackBuff = attackbuff;
        } else {
            foeAttackBuff = attackbuff;
        }
    }

    public void setDefenseBuff(Entity target, double defensebuff) {
        if (target instanceof Player) {
            playerDefenseBuff = defensebuff;
        } else {
            foeDefenseBuff = defensebuff;
        }
    }

    public void calculateFoeRound(List<ICard> eCards) {
        eCards.forEach((card) -> {
            // gameIO.displayMessage("foe choose");

            // gameIO.displayMessage(card.getName());
            if (card instanceof AttackCard) {
                foeAttackDamage += (((AttackCard) card).getDamage() + foeBasicStrength) * foeAttackBuff;// buff
            }
            if (card instanceof DefendCard) {
                foeDefense += (((DefendCard) card).getBlock() + foeBasicDefense) * foeDefenseBuff;
            }
            if (card instanceof SkillCard) {
                ((SkillCard) card).play(foe, this);
            }
        });
        gameIO.displayMessage("Damage To Player: " + foeAttackDamage + " ,Block: " + foeDefense);
    }

    public void calculatePlayerAction(ICard card) {
        gameIO.displayMessage("player choose: " + card.getName());
        if (card instanceof AttackCard) {
            playerAttackDamage = (int) ((((AttackCard) card).getDamage() + playerBasicStrength) * playerAttackBuff);
            if (playerAttackDamage < foeDefense) {
                foeDefense -= playerAttackDamage;
                gameIO.displayMessage("Player Damage To " + foe.getName() + ": 0");
                gameIO.displayMessage(foe.getName() + " Status: Health: " + foe.getHealth() + ", Attack: "
                        + foeAttackDamage + ", Remain Block: " + foeDefense);
            } else {
                gameIO.displayMessage("Player Damage To " + foe.getName() + ": " + (playerAttackDamage - foeDefense));
                foe.takeDamage(playerAttackDamage - foeDefense);
                foeDefense = 0;
                gameIO.displayMessage(foe.getName() + " Status: Health: " + foe.getHealth() + ", Attack: "
                        + foeAttackDamage + ", Remain Block: " + foeDefense);
            }
        }
        if (card instanceof DefendCard) {
            playerDefense += (((DefendCard) card).getBlock() + playerBasicDefense) * playerDefenseBuff;
        }
        if (card instanceof SkillCard) {
            ((SkillCard) card).play(player, this);
        }
    }

    public void calculateRound() { // return record
        foeAttackDamage -= playerDefense;
        if(foeAttackDamage < 0){
            playerDefense -= foeAttackDamage;
            foeAttackDamage = 0;
        }
        gameIO.displayMessage(foe.getName() +" Demage To Player: " + foeAttackDamage);
        player.takeDamage(foeAttackDamage);
        gameIO.displayMessage(player.getName() + " Status: Health: " + player.getHealth());


        if (playerAttackDamage > playerMaxDemage) {
            playerMaxDemage = playerAttackDamage;
        }
        if (playerDefense > playerMaxDefense) {
            playerMaxDefense = playerDefense;
        }
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
        playerAttackDamage = 0;
        foeAttackDamage = 0;
        playerDefense = 0;
        foeDefense = 0;
        playerAttackBuff = 0;
        playerDefenseBuff = 0;
        foeAttackBuff = 0;
        foeDefenseBuff = 0;
        playerHeal = 0;
        foeHeal = 0;
    }
}
