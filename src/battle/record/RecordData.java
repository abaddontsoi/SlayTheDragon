package battle.record;

import entity.Entity;
import record.turnDataType.TurnDataType;
import card.ICard;

public class RecordData {

//	data type, damage, heal or defense
	private TurnDataType type;
	private double value;
	
//	From and to, can change to other types (such as entity status)
	private Entity from, to;
	// to is the target of the effect
	
	private ICard card; // optional
	
	
	public RecordData(TurnDataType type, double value, Entity from, Entity to) {
		this.type = type;
		this.value = value;
		
		this.from = from;
		this.to = to;
	}

//	for effects that only apply to entity itself
	public RecordData(TurnDataType type, double value, Entity to) {
		this.type = type;
		this.value = value;
		
		this.to = to;
	}
	
	public double getValue() {
		return this.value;
	}

	public TurnDataType getType() {
		return this.type;
	}
	
	public Entity getFrom() {
		return this.from;
	}
	
	public Entity getTo() {
		return this.to;
	}
	
	public void setCard(ICard c) {
		this.card = c;
	}
	public ICard getCard() {
		return this.card;
	}
	
	@Override
	public String toString() {
		return type.toString() +": "+ this.value;
	}
	
	public static double findMax(RecordData data, double max) {
		return Math.max(data.value, max);
	}
	
	public static double findMin(RecordData data, double min) {
		return Math.min(data.value, min);
	}
}
