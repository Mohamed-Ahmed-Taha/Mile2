package model.characters;


import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}

	@Override
	public void attack() throws NotEnoughActionsException, InvalidTargetException {
		if(getTarget() == null) throw new InvalidTargetException();


		super.attack();
		if(!isSpecialAction()){
			setActionsAvailable(getActionsAvailable() - 1);}

	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		super.useSpecial();
		if(getActionsAvailable() == 0) throw new NotEnoughActionsException("No more actions available");

	}


}
