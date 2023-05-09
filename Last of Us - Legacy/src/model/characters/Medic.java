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
	public void attack() throws NotEnoughActionsException, InvalidTargetException {
		super.attack();
		setActionsAvailable(getActionsAvailable() - 1);
	}

	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if(!(this.getTarget() instanceof Hero)) throw new InvalidTargetException("You must select a Hero to heal");
		if(!(isAdjacent(getTarget(), this))) throw new InvalidTargetException("You should select a close Hero to heal");
		if(getActionsAvailable() == 0) throw new NotEnoughActionsException("No more actions available");
		super.useSpecial();

		Character healed = getTarget();
		healed.setCurrentHp(1000);
	}


}
