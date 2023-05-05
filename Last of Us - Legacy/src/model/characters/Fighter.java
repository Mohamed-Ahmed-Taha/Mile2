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
		if(getSupplyInventory().isEmpty()) throw new NoAvailableResourcesException("You don't have enough supply to preform this action");
		if(!(isAdjacent(getTarget(), this))) throw new InvalidTargetException("You should select a close Zombie to attack");
		if(getActionsAvailable() == 0) throw new NotEnoughActionsException("No more actions available");
		if(!(this.getTarget() instanceof Zombie)) throw new InvalidTargetException("You must select a Zombie to attack");


		Supply s = this.getSupplyInventory().get(0);
		s.use(this);
		super.setSpecialAction(true);

		setActionsAvailable(getActionsAvailable() - 1);
		//if(Game.checkEndTurn()) Game.endTurn();

	}


}
