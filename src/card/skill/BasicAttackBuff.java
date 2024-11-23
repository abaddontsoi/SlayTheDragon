package card.skill;


public class BasicAttackBuff extends Attackbuff {
	public BasicAttackBuff(){
        super(1.5);
    }

	@Override
	public String getName() {
		return "Baic Attack Buff";
	}
	public double getatkbuff() {
		return mag_damage;
	}

	@Override
	public String getDescription() {
		return "Attack buffed " + getatkbuff() + " times.";
	}

}
