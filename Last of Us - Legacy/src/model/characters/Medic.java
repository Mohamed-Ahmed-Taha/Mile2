package model.characters;


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
		if(!getSupplyInventory().isEmpty()){
			super.setSpecialAction(true);}
		Supply s = new Supply();
		s.use(this);
		if(super.getTarget() instanceof Hero && Character.isAdjacent(super.getTarget(),this)){
		Character healed = super.getTarget();
		healed.setCurrentHp(1000);}
		else throw new InvalidTargetException("You should select a close Hero to heal");

	}


}
