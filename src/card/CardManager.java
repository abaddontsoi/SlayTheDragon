
package card;

import java.util.*;

import entity.Entity;
import entity.Foe;
import entity.Hand;
import entity.Player;
import gameIO.GameIO;

public class CardManager {
    private Entity entity; 
    private Deque<ICard> deck;
    private List<ICard> discardPile;
    private Random random;
    private static final int INITIAL_HAND_SIZE = 5;
    private Hand hand;
    private GameIO gameIO;


    public CardManager(List<ICard> initialDeck, Entity entity) {
        this.random = new Random();
        this.entity = entity;
        Collections.shuffle(initialDeck, random);
        // for (ICard iCard : initialDeck) {
        //     System.err.println(iCard.getName() + "---" + iCard.getDescription());
        // }
        this.deck = new ArrayDeque<>(initialDeck);
        this.discardPile = new ArrayList<>();
        this.hand = new Hand();
        gameIO = GameIO.getInstance();
    }

    // public void drawCards(Entity entity) {
    //     while (entity.getHandCards().size() < INITIAL_HAND_SIZE) {
    //         entity.addCardToHand(drawCard());
    //     }
    // }

    public void drawCards() {
        while (hand.size() < INITIAL_HAND_SIZE) {
            hand.addCardToHand(drawCard());
        }
    }

    public ICard drawCard() {
        if (deck.isEmpty()) {
            replenishDeck();
        }
        return deck.pop();
    }

    public void discardCard(ICard card) {
        discardPile.add(card);
    }

    private void replenishDeck() {
        Collections.shuffle(discardPile, random);
        deck.addAll(discardPile);
        discardPile.clear();
    }

    // public void addCardToDeck(ICard card) {
    // deck.add(card);
    // }

    public void initializeTurn() {
        drawCards();
    }

    public List<ICard> chooseCards() {
        List<ICard> chosenCards = new ArrayList<>();
        // Check if the hand is empty
        if (this.hand.isEmpty()) {
            drawCards();
        }

        if(entity instanceof Player){
            // Prompt the player to choose CHOSEN_CARDS_SIZE cards
            for (int i = 0; i < Entity.CHOSEN_CARDS_SIZE; i++) {
                ICard chosenCard = gameIO.promptCardSelection(hand.getCards());
                // add the chosen card to the chosen cards
                chosenCards.add(chosenCard);
                // remove the chosen card from the hand
                // and put it in the discard pile
                hand.removeCardFromHand(chosenCard);
                discardCard(chosenCard);
            }

            // Put rest of the hand back to the discarded cards
            for (ICard card : hand.getCards()) {
                discardCard(card);
            }

            // Clear the hand
            hand.clear();

            return chosenCards;
            
        }
        
        if (entity instanceof Foe){
            if (chosenCards.isEmpty()) {
                drawCards();
            }
    
            // Choose the first CHOSEN_CARDS_SIZE cards
            // put them in the chosen cards and discard them
            for (int i = 0; i < Entity.CHOSEN_CARDS_SIZE; i++) {
                ICard chosenCard = hand.getCard(i);
                chosenCards.add(chosenCard);
                hand.removeCardFromHand(chosenCard);
                discardCard(chosenCard);
            }
    
            // Put rest of the hand back to the discarded cards
            for (ICard card : hand.getCards()) {
                discardCard(card);
            }
    
            // Clear the hand
            hand.clear();
    
            return chosenCards;
        }
        return null;
    }
}
