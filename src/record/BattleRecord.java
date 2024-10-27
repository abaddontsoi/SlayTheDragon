package record;

import entity.Player;
import entity.Foe;

public class BattleRecord extends Record {

	public BattleRecord(Player p, Foe f) {
		super(p, f);
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		for (IDataItem data: super.getDataItems()) {
			data.print();
		}
	}

	@Override
	public void get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void set() {
		// TODO Auto-generated method stub

	}

}
