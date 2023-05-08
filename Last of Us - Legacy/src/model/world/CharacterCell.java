package model.world;

import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;

public class CharacterCell extends Cell {

	private Character character;
	
	private boolean isSafe;
	
	public CharacterCell(Character character, boolean isSafe) {
		
		this.character = character;
		
		this.isSafe = isSafe;
	
	}
	
	public CharacterCell(Character character) {
	
		this.character = character;
		
		this.isSafe = false;
	
	}
	
	
	public CharacterCell() {
		
		
	}
	
	public Character getCharacter() {
	
		return character;
	
	}
	
	public void setCharacter(Character character) {
	
		this.character = character;
	
	}
	
	
	public boolean isSafe() {
	
		return isSafe;
	
	}
	
	public void setSafe(boolean isSafe) {
	
		this.isSafe = isSafe;
	
	}
	
	public String toString() {
		
//		if(isVisible() == false)
//			return "[ ]";
		
		if (character instanceof Fighter)
			return "[f] ";
		if (character instanceof Medic)
			return "[m] ";
		if (character instanceof Explorer)
			return "[e] ";
		if (character instanceof Zombie)
			return "[z] ";
		return "[ ] ";
	}

}
