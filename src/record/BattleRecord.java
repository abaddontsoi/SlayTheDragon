package record;

import entity.Player;
import entity.EntityStatus;
import entity.Foe;

public class BattleRecord extends Record {

	public BattleRecord(EntityStatus p, EntityStatus f) {
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
	public Record get() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void set() {
		// TODO Auto-generated method stub

	}

}
