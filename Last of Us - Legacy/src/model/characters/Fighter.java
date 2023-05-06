package model.characters;


import engine.Game;
import model.collectibles.*;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}


	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		super.useSpecial();
		if(!(isAdjacent(getTarget(), this))) throw new InvalidTargetException("You should select a close Zombie to attack");
		if(getActionsAvailable() == 0 && !isSpecialAction()) throw new NotEnoughActionsException("No more actions available");
		if(!(this.getTarget() instanceof Zombie)) throw new InvalidTargetException("You must select a Zombie to attack");

	}


}
