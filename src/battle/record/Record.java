package battle.record;

import java.util.ArrayList;
import java.util.List;

import battle.FoeData;
import battle.PlayerData;
import card.ICard;
import entity.*;
import record.turnDataType.TurnDataType;

public abstract class Record {

	private static List<Record> records = new ArrayList<Record>();
	private static List<ICard> deck = new ArrayList<ICard>();
	private static List<Entity> foesFaced = new ArrayList<Entity>();
	
	private PlayerData playerData;
	private FoeData foeData;
	
	protected Record(PlayerData p, FoeData f) {
		this.playerData = p;
		this.foeData = f;
	}
	
	public static void addRecord(Record r) {
		records.add(r);
	}
	
//	Data required from whole game:
//	1.	Final deck
	public static List<ICard> getFinalDeck() {
		return deck;
	}
	public static void setFinalDeck(List<ICard> d) {
		Record.deck = d;
	}
	
//	2.	Most frequently played card
	public static ICard getMostFrequentlyPlayedCard() {
//		should have calculation
		return null;
	}
	
//	3.	Total damage received
	public static int getTotalDamageReceived() {
		int sum = 0;
		
		return sum;
	}
	
//	4.	Total damage blocked
	public static double getTotalDamageBlocked() {
		double sum = 0;
//		for (Record r : Record.records) {
//			sum += r.getTotalBlockedDamage();
//		}
		return sum;
	}
	
//	5.	Total healing in game
	public static double getTotalHealingInGame() {
		double sum = 0;
		for (Record r : Record.records) {
			sum += r.playerData.getTotalHeal();
		}
		return sum;
	}
	
//	6.	Lowest health after battle
	public static double getLowestHealthAfterBattle() {
		double lowestHealth = 0;
//		if (records.size() > 0) {
//			lowestHealth = records.get(0).getPlayerStatus().getHealth();
//		}
//		for (int i = 1; i < records.size(); i++) {
//			double currentHealth = records.get(0).getPlayerStatus().getHealth();
//			if (currentHealth < lowestHealth) {
//				lowestHealth = currentHealth;
//			}
//		}
		return lowestHealth;
	}
	
//	7.	Max and min # of rounds in 1 battle
	public static int getMaxRoundsInBattle() {
		int max = 0;
		for (Record r : records) {
//			if r.roundNumbers > max, then max = r.roundNumbers
			max = Math.max(max, r.playerData.getRounds());
		}
		return max;
	}
	public static int getMinRoundsInBattle() {
		int min = 0;
		if (records.size() > 0) {
//			min = records.get(0).getTotalRounds();
		}
		for (int i = 1; i < records.size(); i++) {
//			if r.roundNumbers < min, then min = r.roundNumbers
//			min = Math.min(min, records.get(i).getTotalRounds());
		}
		return min;
	}
	
//	8.	Max and min # of cards played in 1 battle
	public static int getMaxCardsPlayedInBattle() {
		int max = 0;
		for (Record r : records) {
//			if (r.getNumOfPlayedCards() > max) {
//				max = r.getNumOfPlayedCards();
//			}
		}
		return max;
	}
	public static int getMinCardsPlayedInBattle() {
		int min = 0;
		if (records.size() > 0) {
//			min = records.get(0).getNumOfPlayedCards();
		}
		for (int i = 1; i < records.size(); i++) {
//			if r.roundNumbers < min, then min = r.roundNumbers
//			if (records.get(i).getNumOfPlayedCards() < min) {
//				min = records.get(i).getNumOfPlayedCards();
//			}
		}
		return min;
	}
	
//	9.	All collected rewards
	public static List<ICard> getAllRewards(){
		return null;
	}
	
//	10.	All foes faced
	public static List<Entity> getAllFacedFoes() {
		for (Record r : Record.records) {
			Record.foesFaced.add(r.foeData.getEntity());
		}
		return foesFaced;
	}

}
