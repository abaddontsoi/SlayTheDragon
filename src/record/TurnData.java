package record;

import record.turnDataType.TurnDataType;
import entity.Entity;

public class TurnData {

//	data type, damage, heal or defense
	private TurnDataType type;
	private double value;
	
//	From and to, can change to other types
	private Entity from, to;
	
	
	public TurnData(TurnDataType type, double value, Entity from, Entity to) {
		this.type = type;
		this.value = value;
		
		this.from = from;
		this.to = to;
	}

	public TurnData get() {
		return this;
	}

	@Override
	public String toString() {
		return type.getTypeName() +": "+ this.value;
	}
}
