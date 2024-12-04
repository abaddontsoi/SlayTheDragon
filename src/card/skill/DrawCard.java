package card.skill;
import battle.*;
import card.ICard;
import card.defend.DefendCard;
import entity.Entity;
import entity.Player;

public abstract class DrawCard extends SkillCard{
	protected int noDrawCard;
	
	public DrawCard(int noDrawCard) {
		this.noDrawCard = noDrawCard;
	}

	@Override
	public void play(Entity target, ComputeCenter cal) {

	}

	// @Override
	// public String getName() {
	// 	return "Draw Card";
	// }

	@Override
	public String getDescription() {
		return "Drawn " + this.noDrawCard + " cards.";
	}

	public int getNoDrawCard(){
		return noDrawCard;
	}
    

}
