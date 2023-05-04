package model.characters;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		
		if(super.getTarget() instanceof Zombie)
			throw new InvalidTargetException();
		
		super.attack();
		
		
	}
	
	
	public void onCharacterDeath() {
		
		
		// delete zombie
		Game.zombies.remove(this);
		super.onCharacterDeath();

		
		Zombie z = new Zombie();
		Game.addToMap(z);
	}
	
	

}


