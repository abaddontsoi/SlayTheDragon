package record;

import java.util.ArrayList;
import java.util.List;
import entity.*;

public abstract class Record {

	private static List<Record> records = new ArrayList<Record>();
	
	
	private EntityStatus playerStatus;
	private EntityStatus foeStatus;
	
	private List<IDataItem> turnData = new ArrayList<IDataItem>();
	
	public Record(EntityStatus p, EntityStatus f) {
		this.playerStatus = p;
		this.foeStatus = f;
		
		Record.records.add(this);
	}
	
	public List<IDataItem> getDataItems() {
		return turnData;
	}
	
	public abstract Record get();
	public abstract void set();
	
	public void print() {
		
	}
	
	public EntityStatus getPlayerStatus() {
		return this.playerStatus.getStatusCopy();
	}
	
	public EntityStatus getFoeStatus() {
		return this.foeStatus.getStatusCopy();
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
