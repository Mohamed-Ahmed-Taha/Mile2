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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException{

		Character t = Game.getAdjHero(this.getLocation());

		if(t != null){
			this.setTarget(t);
			super.attack();
		}

		setTarget(null);
		
	}
	
	
	public void onCharacterDeath() {

		Game.zombies.remove(this);
		Point p = this.getLocation();
//		((CharacterCell) Game.map[p.x][p.y]).setCharacter(null);
		Game.map[p.x][p.y] = new CharacterCell(null);
		Game.addZombie();
		System.out.println("A zombie died");
//		Game.map[p.x][p.y].setVisible(true);
	}
	
	

}


