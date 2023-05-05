package model.collectibles;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;

public class Supply implements Collectible  {

	
	public Supply() {
		
	}

	
	public void pickUp(Hero h) {

		h.getSupplyInventory().add(this);

		
	}

	
	public void use(Hero h) throws InvalidTargetException, NotEnoughActionsException, NoAvailableResourcesException {
		h.getSupplyInventory().remove(this);
		h.useSpecial();
		
	}
			

}
