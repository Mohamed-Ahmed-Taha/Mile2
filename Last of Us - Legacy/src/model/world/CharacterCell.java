package model.world;

import model.characters.Character;

public class CharacterCell extends Cell {

	private Character character;
	
	private boolean isSafe;
	private boolean isDiscovered;

	public CharacterCell(Character character, boolean isSafe) {
		
		this.character = character;
		
		this.isSafe = isSafe;
	
	}


	
	public CharacterCell(Character character) {
	
		this.character = character;
		
		this.isSafe = false;
	
	}

	public CharacterCell(Character character, Boolean isDiscovered) {

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

	public void setDiscovered(boolean isDiscovered) {

		this.isDiscovered = isDiscovered;

	}

	public boolean isDiscovered() {

		return isDiscovered;

	}
}
