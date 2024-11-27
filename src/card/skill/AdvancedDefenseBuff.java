package card.skill;

public class AdvancedDefenseBuff extends Defensebuff{
    public AdvancedDefenseBuff(){
        super(2);
    }

    @Override
	public String getName() {
		return "Advanced Defense Buff Card";
	}

    public double getdefbuff(){
        return mag_defense;
    }
	@Override
	public String getDescription() {
		return "Defense buffed " + getdefbuff() + " times.";
	}

}
