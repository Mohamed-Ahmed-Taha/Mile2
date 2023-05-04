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
		if(!getSupplyInventory().isEmpty()){
		Supply s = new Supply();
		s.use(this);
		super.setSpecialAction(true);}
	}


}
