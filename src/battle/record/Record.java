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
	
//	2.	Total damage received
	public static int getTotalDamageReceived() {
		int sum = 0;
		for (Record r : Record.records) {
			sum += r.playerData.getReceivedDamage();
		}
		return sum;
	}
	
	
//	3.	Total healing in game
	public static double getTotalHealingInGame() {
		double sum = 0;
		for (Record r : Record.records) {
			sum += r.playerData.getTotalHeal();
		}
		return sum;
	}
	
//	4. Total damage blocked
	public static int getTotalDamageBlocked() {
		int sum = 0;
		for (Record r : Record.records) {
			sum += r.playerData.getTotalDamageBlocked();
		}
		return sum;
	}
	
//	5.	Max and min # of rounds in 1 battle
	public static int getMaxRoundsInBattle() {
		int max = 0;
		for (Record r : records) {
			max = Math.max(max, r.playerData.getRounds());
		}
		return max;
	}
	public static int getMinRoundsInBattle() {
		int min = -1;
		if (records.size() > 0) {
			min = records.get(0).playerData.getRounds();
		}
		for (int i = 1; i < records.size(); i++) {
			min = Math.min(min, records.get(i).playerData.getRounds());
		}
		return min;
	}
	
//	6.	Max and min # of cards played in 1 battle
	public static int getMaxCardsPlayedInBattle() {
		int max = 0;
		for (Record r : records) {
			if (r.playerData.getTotalCardsPlayed() > max) {
				max = r.playerData.getTotalCardsPlayed();
			}
		}
		return max;
	}
	public static int getMinCardsPlayedInBattle() {
		int min = -1;
		if (records.size() > 0) {
			min = records.get(0).playerData.getTotalCardsPlayed();
		}
		for (int i = 1; i < records.size(); i++) {
//			if r.roundNumbers < min, then min = r.roundNumbers
			if (records.get(i).playerData.getTotalCardsPlayed() < min) {
//				min = records.get(i).getTotalCardsPlayed();
				min = Math.min(min, records.get(i).playerData.getTotalCardsPlayed());
			}
		}
		return min;
	}
	
//	7.	All collected rewards
	public static List<ICard> getAllRewards(){
		List<ICard> rewards = new ArrayList<>();
		for (Record r : Record.records) {
			rewards.addAll(r.playerData.getRewards());
		}
		return rewards;
	}
	
//	8.	All foes faced
	public static List<Entity> getAllFacedFoes() {
		for (Record r : Record.records) {
			Record.foesFaced.add(r.foeData.getEntity());
		}
		return foesFaced;
	}

}
