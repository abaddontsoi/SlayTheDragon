package record;

import java.util.ArrayList;
import java.util.List;

import card.ICard;
import entity.*;
import record.turnDataType.TurnDataType;

public abstract class Record {

	private static List<Record> records = new ArrayList<Record>();
	private static List<ICard> deck = new ArrayList<ICard>();
	private static List<Entity> foesFaced = new ArrayList<Entity>();
	
	private Entity player;
	private EntityStatus playerInitialStatus;
	private Entity foe;
	private EntityStatus foeInitialStatus;
	
	private List<RecordData> recordData = new ArrayList<RecordData>();
	
	public Record(Entity p, Entity f) {
		this.player = p;
		this.foe = f;
		
		this.playerInitialStatus = p.getEntityStatus();
		this.foeInitialStatus = f.getEntityStatus();
		
		addFacedFoe(this.foe);
		
		records.add(this);
	}
	
	public List<RecordData> getDataItems() {
		return recordData;
	}
	
	public EntityStatus getPlayerStatus() {
		return this.playerInitialStatus.getStatusCopy();
	}
	
	public EntityStatus getFoeStatus() {
		return this.foeInitialStatus.getStatusCopy();
	}
	
//	Call the following 2 methods in battle control
	public void createTurnData(TurnDataType type, double value, Entity from, Entity to) {
		this.recordData.add(new RecordData(type, value, from, to));
	}
	public void createTurnData(TurnDataType type, double value, Entity to) {
		this.recordData.add(new RecordData(type, value, to));
	}
	
//	Data required from battle: (remove code smell)
//	1.	# of cards played
	public int getNumOfPlayedCards() {
		int count = 0;
		for (RecordData data: recordData) {
			if (data.getType() == TurnDataType.PlayCardType) {
				count += data.getValue();
			}
		}
		return count;
	}

//	2.	Max damage dealt in 1 round (in current battle)
	public double getMaxDamageDealt() {
		double max = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.AttackType && data.getFrom() == this.player) {
				max = RecordData.findMax(data, max);
			}
		}
		return max;
	}
	
//	3.	Max damage blocked in 1 round (in current battle)
	public double getMaxDamageBlocked() {
		double max = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.DefendType && data.getFrom() == this.player) {
				max = RecordData.findMax(data, max);
			}
		}
		return max;
	}
	
//	4.	Total Blocked damage
	public double getTotalBlockedDamage() {
		double total = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.DefendType
					&& data.getTo() == this.player) {
				total += data.getValue();
			}
		}
		return total;
	}
	
	
//	5.	Total Received damage (from card and effect)
	public double getTotalReceivedDamage() {
		double total = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.ReceiveDamageType && data.getTo() == this.player) {
				total += data.getValue();
			}
		}
		return total;
	}

	
//	6.	Total Healing
	public double getTotalHealing() {
		double total = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.HealType && data.getTo() == this.player) {
				total += data.getValue();
			}
		}
		return total;
	}

//	7. Total Rounds
	public int getTotalRounds () {
		int rounds = 0;
		for (RecordData data: this.recordData) {
			if (data.getType() == TurnDataType.StartRoundType && data.getTo() == this.player) {
				rounds += 1;
			}
		}
		return rounds;
	}

	
	// static methods

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
			max = Math.max(max, r.getTotalRounds());
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
			min = Math.min(min, records.get(i).getTotalRounds());
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
	public static List<ICard> getAllRewards(){
		return null;
	}
	
//	10.	All foes faced
	public static void addFacedFoe(Entity f) {
		foesFaced.add(f);
	}
	public static List<Entity> getAllFacedFoes() {
		return foesFaced;
	}

}
