package model.characters;


import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

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
		
		Character healed = getTarget();
		
		if(!(this.getTarget() instanceof Hero)) throw new InvalidTargetException("You must select a Hero to heal");
		
		if(!(isAdjacent(getTarget(), this)) && !(healed.getLocation().equals(this.getLocation()))) throw new InvalidTargetException("You should select a close Hero to heal");
		super.useSpecial();

		healed.setCurrentHp(healed.getMaxHp());
	}

	public void AIuseSpecial()  {

		Character healed = getTarget();
		super.AIuseSpecial();

		healed.setCurrentHp(healed.getMaxHp());
	}



}
