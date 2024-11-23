package battle.record;

import entity.Player;
import entity.EntityStatus;
import entity.Foe;
import battle.FoeData;
import battle.PlayerData;
import entity.Entity;

public class BattleRecord extends Record {

	private BattleRecord(PlayerData playerData, FoeData foeData) {
		super(playerData, foeData);
	}
	
	public static void createRecord(PlayerData playerData, FoeData foeData) {
		Record.addRecord(new BattleRecord(playerData, foeData));
	}
}
