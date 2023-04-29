package model.characters;

import java.awt.Point;
import java.util.Objects;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;


public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
	

	public Character getTarget() {
		return target;
	}

	
	public void setTarget(Character target) throws InvalidTargetException {
		this.target = target;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public Point getLocation() {
		return location;
	}
	

	public void setLocation(Point location) {
		this.location = location;
	}

	
	public int getMaxHp() {
		return maxHp;
	}

	
	public int getCurrentHp() {
		return currentHp;
	}

	
	public void setCurrentHp(int currentHp) {
		if(currentHp <= 0) { 
			this.currentHp = 0;
			onCharacterDeath();
		}
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	
	public int getAttackDmg() {
		return attackDmg;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return attackDmg == other.attackDmg && currentHp == other.currentHp && Objects.equals(location, other.location)
				&& maxHp == other.maxHp && Objects.equals(name, other.name) && Objects.equals(target, other.target);
	}


	public boolean isAdjacent(Character A, Character B) {
		Point a = A.getLocation(); 
		Point b = B.getLocation();
		return (a.distance(b) >= 1 && a.distance(b) <= Math.sqrt(2));
	}
	
	
	public void attack() throws NotEnoughActionsException{
		
		
		if(isAdjacent(target, this))
			target.setCurrentHp(target.getCurrentHp() - attackDmg);
		
		target.defend(this);
	}
	
	
	public void defend(Character c) {
		c.setCurrentHp(c.getCurrentHp() - attackDmg/2);
	}
	
	
	public void onCharacterDeath() {
		
		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 15; j++) 
				if(Game.map[i][j] instanceof CharacterCell &&
				    ((CharacterCell)Game.map[i][j]).getCharacter().equals(this)) {
					
					Game.map[i][j] = null;
				}
	}
	


//	public static void main(String[] args) {
//		Point a = new Point(0,0);
//		Point b = new Point(1,1);
//		System.out.println(a.distance(b) == Math.sqrt(2));
//	}
}
