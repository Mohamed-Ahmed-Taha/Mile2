package model.world;

import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

public class CollectibleCell extends Cell {

	private Collectible collectible;
	
	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}
	
	public String toString() {
		
//		if(isVisible() == false)
//			return "[ ]";
		
		if (collectible instanceof Vaccine)
			return "[v] ";
		if (collectible instanceof Supply)
			return "[s] ";
		
		return "[ ] ";
	}


}
