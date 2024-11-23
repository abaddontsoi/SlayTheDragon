package card.skill;

public class BasicDefenseBuff extends Defensebuff{
    public BasicDefenseBuff(){
        super(1.5);
    }

    @Override
	public String getName() {
		return "Basic Defense Buff Card";
	}

    public double getdefbuff(){
        return mag_defense;
    }
	@Override
	public String getDescription() {
		return "Defense buffed " + getdefbuff() + " times.";
	}

}
