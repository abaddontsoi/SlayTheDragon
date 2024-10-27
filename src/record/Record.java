package record;

import java.util.ArrayList;
import entity.Player;
import entity.Foe;

public abstract class Record implements IDataItem {

	private static ArrayList<Record> records;
	
	private Player player;
	private Foe foe;
	private ArrayList<IDataItem> dataItems;
	
	public Record(Player p, Foe f) {
		this.player = p;
		this.foe = f;
		this.dataItems = new ArrayList<IDataItem>() ;
	}
	
	public ArrayList<IDataItem> getDataItems() {
		return dataItems;
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
}