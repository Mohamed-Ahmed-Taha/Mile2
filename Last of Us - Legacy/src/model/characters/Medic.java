package model.characters;


import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;

public class Medic extends Hero {
	//Heal amount  attribute - quiz idea
	

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}

	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if(getSupplyInventory().isEmpty()) throw new NoAvailableResourcesException("You don't have enough supply to preform this action");
		if(!(isAdjacent(getTarget(), this))) throw new InvalidTargetException("You should select a close Hero to heal");
		if(getActionsAvailable() == 0) throw new NotEnoughActionsException("No more actions available");
		if(!(this.getTarget() instanceof Hero)) throw new InvalidTargetException("You must select a Hero to heal");

		this.setSpecialAction(true);
		Supply s = this.getSupplyInventory().get(0);
		s.use(this);

		Character healed = getTarget();
		healed.setCurrentHp(1000);

		setActionsAvailable(getActionsAvailable() - 1);
		//if(Game.checkEndTurn()) Game.endTurn();

	}


}
