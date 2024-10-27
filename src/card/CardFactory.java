package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardFactory {
    private static List<ICard> CardFactory = new ArrayList<>();
    public static ICard createRandomCard(){
        Random random = new Random();
        int randomIndex = random.nextInt(CardFactory.size());
        return CardFactory.get(randomIndex);
    }

    
}
