package record;

public class TurnData {

//	data type, damage, heal or defense
	private TurnDataType type;
	private double value;
	
	public TurnData(TurnDataType type, double value) {
		this.type = type;
		this.value = value;
	}

	public TurnData get() {
		return this;
	}

	@Override
	public String toString() {
		return type.getTypeName() +": "+ this.value;
	}
}
