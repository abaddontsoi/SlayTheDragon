package card;

import card.attack.*;
import card.defend.*;
import card.skill.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CardFactory {
	private static CardFactory instance;
	private static Map<CardName, Supplier<ICard>> cardCreators;

	private CardFactory() {
		initializeCardCreators();
	}

	public static CardFactory getInstance() {
		if (instance == null) {
			instance = new CardFactory();
		}
		return instance;
	}

	// public ICard playerDrawCard(){
	// int randomIndex = (int) (Math.random() * playerCardPool.size());
	// return playerCardPool.get(randomIndex);
	// }

	// public ICard createCard(String cardName) {
	// switch (cardName) {
	// case "AttackCard":
	// return new AttackCard(5);
	// case "DefendCard":
	// return new DefendCard(5);
	// case "PoisonCard":
	// // Effect: The enemy loses 5 health for 2 turns
	// return new PoisonCard(5);
	// case "HealCard":
	// // Effect: Heal the player for 5 health
	// return new HealCard(5);
	// case "StrengthCard":
	// // Effect: The player deals 50% more damage for
	// // 2 turns
	// return new StrengthCard(5);
	// case "WeaknessCard":
	// // Effect: The enemy deals 50% less damage for
	// // 2 turns
	// return new WeaknessCard(5);
	// case "VulnerableCard":
	// // Effect: The enemy takes 50% more damage for
	// // 2 turns
	// return new VulnerableCard(5);
	// case "DrawCard":
	// // Effect: Draw 2 cards
	// return new DrawCard(5);
	// case "EnergyCard":
	// // Effect: Gain 1 energy
	// return new EnergyCard(5);
	// case "RitualCard":
	// // Effect: The player gains 1 strength and 1 defense
	// // for 2 turns
	// return new RitualCard(5);
	// case "SacrificeCard":
	// // Effect: The player loses 5 health but gains 1 energy
	// // and 1 strength for 2 turns
	// return new SacrificeCard(5);
	// default:
	// return null;
	// }
	// }
	//

	private void initializeCardCreators() {
		cardCreators = new HashMap<>();
		cardCreators.put(CardName.ATTACK_BASIC, BasicAttackCard::new);
		cardCreators.put(CardName.ATTACK_ADVANCED, AdvancedAttackCard::new);
		cardCreators.put(CardName.DEFEND_BASIC, BasicDefendCard::new);
		cardCreators.put(CardName.DEFEND_ADVANCED, AdvancedDefendCard::new);
		cardCreators.put(CardName.SKILL_BASIC_HEAL, BasicHealCard::new);
		cardCreators.put(CardName.SKILL_ADVANCED_HEAL, AdvancedHealCard::new);
		cardCreators.put(CardName.SKILL_BASIC_POISON, BasicPoisonCard::new);
		cardCreators.put(CardName.SKILL_ADVANCED_POISON, AdvancedPoisonCard::new);
		cardCreators.put(CardName.SKILL_BASIC_DRAW, BasicDrawCard::new);
		cardCreators.put(CardName.SKILL_BASIC_ATTACK_BUFF, BasicAttackBuff::new);
		cardCreators.put(CardName.SKILL_ADVANCED_ATTACK_BUFF, AdvancedAttackBuff::new);
		cardCreators.put(CardName.SKILL_BASIC_DEFENSE_BUFF, BasicDefenseBuff::new);
		cardCreators.put(CardName.SKILL_ADVANCED_DEFENSE_BUFF, AdvancedDefenseBuff::new);
	}

	public ICard createCard(CardName cardName) {
		Supplier<ICard> creator = cardCreators.get(cardName);
		if (creator != null) {
			return creator.get();
		} else {
			throw new IllegalArgumentException("Unknown card name: " + cardName);
		}
	}

	// public List<ICard> createPlayerDeck() {
	// List<ICard> playerDeck = new ArrayList<>();
	// playerDeck.addAll(initialiseBasicCard());
	// return playerDeck;
	// }

	// private ArrayList<ICard> initialiseBasicCard() {
	// ArrayList<ICard> basicCardDeck = new ArrayList<>();
	//
	// for (int i = 0; i < 4; i++) {
	// ICard attackCard = new AttackCard(5);
	// ICard defenseCard = new DefendCard(5);
	// basicCardDeck.add(attackCard);
	// basicCardDeck.add(defenseCard);
	// }
	// return basicCardDeck;
	// }
}
