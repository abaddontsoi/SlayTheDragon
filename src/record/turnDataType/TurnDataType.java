package record.turnDataType;

public abstract class TurnDataType {
	private String typeName;
	
	public TurnDataType(String name) {
		this.typeName = name;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
}
