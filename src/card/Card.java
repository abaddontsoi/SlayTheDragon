package card;

public class Card {
    // package card.attack;

    // import card.ICard;
    import entity.Entity;
    
        private double damage = 0;
        private double defense = 0;
        private Skill skill = null;
    
        public Card(double damage, double defence, Skill skill ) {
            this.damage = damage; 
            this.defence = defence;
            this.skill = skill;
        }
    
    
        @Override
        public abstract String getName();
    
        // @Override
        // public abstract String getDescription();
    
    }
    

