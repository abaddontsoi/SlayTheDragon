package card;
import card.skill.*;

public abstract class Card {
    
        private double damage = 0;
        private double defense = 0;
        private Skill skill = null;
    
        public Card(double damage, double defense, Skill skill ) {
            this.damage = damage; 
            this.defense = defense;
            this.skill = skill;
        }
    
   
        public abstract String getName();
    
        // @Override
        // public abstract String getDescription();
    
    }	