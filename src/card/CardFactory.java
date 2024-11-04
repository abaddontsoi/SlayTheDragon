package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.smartcardio.Card;

import card.attack.*;
import card.defend.*;
import card.effect.*;

public class CardFactory {
    private static CardFactory instance;
    private ArrayList<ICard> playerCardPool = new ArrayList(); 

    private CardFactory(){
        // intialize cardpool for all card 
    }

    public static CardFactory getInstance(){
        if (instance==null){
            instance = new CardFactory();
        }
        return instance;
    }
    
	public ICard playerDrawCard(){
        int randomIndex = (int) (Math.random() * playerCardPool.size());
        return playerCardPool.get(randomIndex);
    }


    public static ArrayList<ICard> initialBasicCard(){
        ArrayList<ICard> basicCardDeck = new ArrayList<>();

        for (int i = 0; i < 4 ; i++){
            ICard attackCard = new AttackCard(5);
            ICard defenseCard = new DefendCard(5);
            basicCardDeck.add(attackCard);
            basicCardDeck.add(defenseCard);
        }
        return basicCardDeck;
    }
}
