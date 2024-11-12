package battle;

import java.util.List;

import card.ICard;
import entity.Entity;
import entity.Foe;
import entity.Player;

public class Calculator {
    private Entity player;
    private Entity foe;

    public Calculator(Entity player, Entity foe){
        this.player = player;
        this.foe = foe;
    }

    
    public void calculateRound(List<ICard> pCards, List<ICard> eCards) {

    }
    
}
