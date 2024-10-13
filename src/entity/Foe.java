package entity;

import action.*;

public class Foe extends Entity {
	private String name;

	public Foe(String name, double maxHealth, double defense, double strength) {
		super(maxHealth, defense, strength);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void chooseAction(Entity opponent) {
		// Randomly choose an action
		int choice = (int) (Math.random() * 2) + 1;
		switch (choice) {
		case 1:
			new AttackAction(strength).execute(opponent);
			break;
		case 2:
			new DefendAction(defense).execute(this);
			break;
		}
		
		// Print the action chosen
		System.out.println(name + " chose action " + choice);
	}

	public String getName() {
		return name;
	}
}
