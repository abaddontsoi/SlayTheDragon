package card.skill;


public class AdvancedAttackBuff extends Attackbuff {
	public AdvancedAttackBuff(){
        super(2);
    }

	@Override
	public String getName() {
		return "Advanced Attack Buff";
	}
	public double getatkbuff() {
		return mag_damage;
	}

	@Override
	public String getDescription() {
		return "Attack buffed " + getatkbuff() + " times.";
	}

}
