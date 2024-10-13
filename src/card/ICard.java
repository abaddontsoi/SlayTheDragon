package card;

import entity.Entity;

public interface ICard {
   void use(Entity user, Entity target);
    String getName();
}
