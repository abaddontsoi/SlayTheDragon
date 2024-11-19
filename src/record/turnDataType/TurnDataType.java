package record.turnDataType;

public enum TurnDataType {
	PlayCardType("Play Card"), // Entity plays a card
	AttackType("Attack"), // Entity attacks
	HealType("Heal"), // Entity heals
	DefendType("Defend"), // Entity add defense 
	ReceiveDamageType("Receive Damage"), // Entity receive damage to health
	BlockDamageType("Block Damage"), // Entity blocks damage successfully
	StartRoundType("Start Round");
	
	private final String type;
	
	private TurnDataType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		if (this == PlayCardType) {
			return "Play Card";
		}

		if (this == AttackType) {
			return "Attack";			
		}
		
		if (this == HealType) {
			return "Heal";
		}
		
		if (this == DefendType) {
			return "Defend";
		}
		
		if (this == ReceiveDamageType) {
			return "Receive Damage";
		}
		
		if (this == BlockDamageType) {
			return "Block Damage";
		}
		
		if (this == StartRoundType) {
			return "Start Round";
		}
		return "";
	}
}