package model.characters;


import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;

public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}

	@Override
	public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException {
		if(getSupplyInventory().isEmpty()) throw new NoAvailableResourcesException();
		if(getActionsAvailable() == 0) throw new NotEnoughActionsException();

		Supply s = this.getSupplyInventory().get(0);
		s.use(this);
		this.setSpecialAction(true);

		for(int i = 0; i <15; i++){
			for(int j = 0; j <15; j++){
				Game.map[i][j].setVisible(true);
			}
		}
		setActionsAvailable(getActionsAvailable() - 1);
		//if(Game.checkEndTurn()) Game.endTurn();


	}
	
	


}
