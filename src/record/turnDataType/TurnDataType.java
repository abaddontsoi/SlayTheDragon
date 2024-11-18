package record.turnDataType;

public enum TurnDataType {
	PlayCardType, // Entity plays a card
	AttackType, // Entity attacks
	HealType, // Entity heals
	DefendType, // Entity add defense 
	ReceiveDamageType, // Entity receive damage to health
	BlockDamageType; // Entity blocks damage successfully
	
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
		return "";
	}
}
