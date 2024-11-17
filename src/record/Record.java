package record;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import entity.*;

public abstract class Record {

	private static List<Record> records = new ArrayList<Record>();
	private static List<ICard> deck = new ArrayList<ICard>();
	
	private EntityStatus playerStatus;
	private EntityStatus foeStatus;
	
	private List<IDataItem> turnData = new ArrayList<IDataItem>();
	
	public Record(EntityStatus p, EntityStatus f) {
		this.playerStatus = p;
		this.foeStatus = f;
		
		records.add(this);
	}
	
	public List<IDataItem> getDataItems() {
		return turnData;
	}
	
	public abstract Record get();
	public abstract void set();
	
	public void print() {
		for (IDataItem data: turnData) {
			data.print();
		}
	}
	
	public EntityStatus getPlayerStatus() {
		return this.playerStatus.getStatusCopy();
	}
	
	public EntityStatus getFoeStatus() {
		return this.foeStatus.getStatusCopy();
	}
	

//	Data required from battle: (remove code smell)
//	1.	# of cards played
	public int getNumOfPlayedCards() {
		return 0;
	}
	public void setNumOfPlayedCards() {
		
	}

//	2.	Max damage dealt in 1 round (in current battle)
	public double getMaxDamageDealt() {
		return 0;
	}
	public void setMaxDamageDealt() {
		
	}
	
//	3.	Max damage blocked in 1 round (in current battle)
	public double getMaxDamageBlocked() {
		return 0;
	}
	public void setMaxDamageBlocked() {
		
	}
	
//	4.	Total Blocked damage
	public double getTotalBlockedDamage() {
		return 0;
	}
	public void setTotalBlockedDamage() {

	}
	
//	5.	Total Received damage (from card and effect)
	public double getTotalReceivedDamage() {
		return 0;
	}
	public void setTotalReceivedDamage() {
		
	}
	
//	6.	Total Healing
	public double getTotalHealing() {
		return 0;
	}
	public void setTotalHealing() {
		
	}
//	7. Total Rounds
	public int getTotalRounds () {
		return 0;
	}

	
	// static methods
	public static void printBattle() {
		for (Record r: records) {
			r.print();
		}
	}

	public static void createRecord(Record r) {
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
	public static void setMostFrequentlyPlayedCard() {
		
	}
	
//	3.	Total damage received
	public static double getTotalDamageReceived() {
		double sum = 0;
		for (Record r : Record.records) {
			sum += r.getTotalReceivedDamage();
		}
		return sum;
	}
	
//	4.	Total damage blocked
	public static double getTotalDamageBlocked() {
		double sum = 0;
		for (Record r : Record.records) {
			sum += r.getTotalBlockedDamage();
		}
		return sum;
	}
	
//	5.	Total healing in game
	public static double getTotalHealingInGame() {
		double sum = 0;
		for (Record r : Record.records) {
			sum += r.getTotalHealing();
		}
		return sum;
	}
	
//	6.	Lowest health after battle
	public static double getLowestHealthAfterBattle() {
		double lowestHealth = 0;
		if (records.size() > 0) {
			lowestHealth = records.get(0).getPlayerStatus().getHealth();
		}
		for (int i = 1; i < records.size(); i++) {
			double currentHealth = records.get(0).getPlayerStatus().getHealth();
			if (currentHealth < lowestHealth) {
				lowestHealth = currentHealth;
			}
		}
		return lowestHealth;
	}
	
//	7.	Max and min # of rounds in 1 battle
	public static int getMaxRoundsInBattle() {
		int max = 0;
		for (Record r : records) {
//			if r.roundNumbers > max, then max = r.roundNumbers
		}
		return max;
	}
	public static int getMinRoundsInBattle() {
		int min = 0;
		if (records.size() > 0) {
			min = records.get(0).getTotalRounds();
		}
		for (int i = 1; i < records.size(); i++) {
//			if r.roundNumbers < min, then min = r.roundNumbers
		}
		return min;
	}
	
//	8.	Max and min # of cards played in 1 battle
	public static int getMaxCardsPlayedInBattle() {
		int max = 0;
		for (Record r : records) {
			if (r.getNumOfPlayedCards() > max) {
				max = r.getNumOfPlayedCards();
			}
		}
		return max;
	}
	public static int getMinCardsPlayedInBattle() {
		int min = 0;
		if (records.size() > 0) {
			min = records.get(0).getNumOfPlayedCards();
		}
		for (int i = 1; i < records.size(); i++) {
//			if r.roundNumbers < min, then min = r.roundNumbers
			if (records.get(i).getNumOfPlayedCards() < min) {
				min = records.get(i).getNumOfPlayedCards();
			}
		}
		return min;
	}
	
//	9.	All collected rewards
	
//	10.	All foes faced
	public static List<Foe> getAllFacedFoes() {
		return null;
	}

}
