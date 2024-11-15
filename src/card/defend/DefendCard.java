package card.defend;

import card.ICard;

public abstract class DefendCard implements ICard {
	private int block;

	public DefendCard(int defense) {
        this.block = defense;
    }
	
	public int getBlock() {
        return  block;
    }
	
	@Override
	public DefendCard clone() {
		try {
			return (DefendCard) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
