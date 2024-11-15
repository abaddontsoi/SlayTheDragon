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

    private int playerAttackDamage;
    private int foeAttackDamage;

    private int playerDefense;
    private int foeDefense;

    private List<ICard> playerEffectsList;
    private List<ICard> foeEffectsList;

    public Calculator(Entity player, Entity foe){
        this.player = player;
        this.foe = foe;
        this.gameIO = GameIO.getInstance();
        this.playerMaxDemage = 0;
        this.playerMaxDefense = 0;
        this.playerHeal = 0;
        this.foeHeal = 0;
        this.playerAttackDamage = 0;
        this.foeAttackDamage = 0;
        this.playerDefense = 0;
        this.foeDefense = 0;
        this.playerEffectsList = new ArrayList<>();
        this.foeEffectsList = new ArrayList<>();
    }


    public void calculateRound(List<ICard> pCards, List<ICard> eCards) {
        pCards.forEach((card) -> {
            gameIO.displayMessage("player choose");

            gameIO.displayMessage(card.getName());
            // switch (card.) {
            //     case value:
                    
            //         break;
            
            //     default:
            //         break;
            // }
            if(card instanceof AttackCard){
                playerAttackDamage += ((AttackCard)card).getDamage();
            }
            if(card instanceof DefendCard){
                playerDefense += ((DefendCard)card).getBlock();
            }
            if(card instanceof SkillCard){
                playerEffectsList.add(card);
            }
        });
        eCards.forEach((card) -> {
            gameIO.displayMessage("foe choose");

            gameIO.displayMessage(card.getName());
            if(card instanceof AttackCard){
                foeAttackDamage += ((AttackCard)card).getDamage();
            }
            if(card instanceof DefendCard){
                foeDefense += ((DefendCard)card).getBlock();
            }
            if(card instanceof SkillCard){
                foeEffectsList.add(card);
            }
        });
        gameIO.displayMessage("========== Card Stat ===========");
        gameIO.displayMessage("playerAttackDamage: " + playerAttackDamage);
        gameIO.displayMessage("playerDefense: " + playerDefense);
        gameIO.displayMessage("foeAttackDamage: " + foeAttackDamage);
        gameIO.displayMessage("foeDefense: " + foeDefense);

        playerAttackDamage -= foeDefense;
        if(playerAttackDamage < 0) {
            playerAttackDamage = 0;
        }

        foeAttackDamage -= playerDefense;
        if(foeAttackDamage < 0) {
            foeAttackDamage = 0;
        } 

        gameIO.displayMessage("========== Result ===========");
        gameIO.displayMessage("playerAttackDamage: " + playerAttackDamage);
        gameIO.displayMessage("playerDefense: " + playerDefense);
        gameIO.displayMessage("foeAttackDamage: " + foeAttackDamage);
        gameIO.displayMessage("foeDefense: " + foeDefense);
        player.takeDamage(foeAttackDamage);
        foe.takeDamage(playerAttackDamage);

    }
    public void reset(){
        playerAttackDamage = 0;
        foeAttackDamage = 0;
        playerDefense = 0;
        foeDefense = 0;
    }
}

    