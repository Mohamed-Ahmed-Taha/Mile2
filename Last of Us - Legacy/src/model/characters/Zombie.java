package model.characters;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{

		if(this.getTarget() instanceof Zombie)
			throw new InvalidTargetException();
		
		super.attack();
		
		
	}
	
	
	public void onCharacterDeath() {

		Game.zombies.remove(this);
		Point p = getLocation();
		Game.map[p.x][p.y] = new CharacterCell(null);
		Game.addZombie();
	}
	
	

}


