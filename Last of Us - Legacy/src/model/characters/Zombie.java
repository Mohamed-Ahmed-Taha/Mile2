package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void onCharacterDeath() {
		
		super.onCharacterDeath();
		Game.zombies.remove(this);
		Zombie z = new Zombie();
		Game.addToMap(z);
	}
	
	
	public void attack() throws InvalidTargetException{
		
		if(super.getTarget() instanceof Zombie)
			throw new InvalidTargetException();	}

}


