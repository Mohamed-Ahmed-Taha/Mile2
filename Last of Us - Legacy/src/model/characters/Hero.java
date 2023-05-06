package model.characters;

import java.awt.*;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.*;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero() {
			
		}
		
		
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name, maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory = new ArrayList<Supply>();
			this.specialAction = false;
		
		}



	public boolean clear(Cell c) throws MovementException{
		if (c instanceof CharacterCell && ((CharacterCell) c).getCharacter() != null){
			return false; }

		if(c instanceof TrapCell){
			this.setCurrentHp(this.getCurrentHp()-((TrapCell) c).getTrapDamage());
			c = new CharacterCell(null);

			 }

		if(c instanceof CollectibleCell){
			if(((CollectibleCell) c).getCollectible() instanceof Supply)
				((CollectibleCell) c).getCollectible().pickUp(this);

			if(((CollectibleCell) c).getCollectible() instanceof Vaccine)
				((CollectibleCell) c).getCollectible().pickUp(this);
		}


		return true;
	}

	public Point newCoord(int x, int y, Direction d){
			Point n;

		n = switch (d) {
			case UP -> new Point(x +1  , y  );
			case DOWN -> new Point(x -1 , y );
			case LEFT -> new Point(x , y - 1 );
			case RIGHT -> new Point(x , y + 1);
		};

		return n;
	}

	public void move(Direction d) throws MovementException, InvalidTargetException, NotEnoughActionsException {
		Point loc = this.getLocation();
		Point n = newCoord(loc.x,loc.y,d);
		if(actionsAvailable == 0) throw new NotEnoughActionsException("Can't move: 0 Actions left");

		if(Game.isEdge(n.x, n.y)) throw new MovementException("Stay within the bounds");

		if(clear(Game.map[n.x][n.y])) {
			this.setLocation(n);
			Game.map[n.x][n.y] = new CharacterCell(this);
			Game.map[loc.x][loc.y] = new CharacterCell(null);
		}

		else throw new MovementException("Cell is occupied by a character");

		//update visibility according to movement if up update the three new cells to be visible
		// but check if edge first
		actionsAvailable--;
		//if(Game.checkEndTurn()) Game.endTurn();
	}





		public void attack() throws NotEnoughActionsException, InvalidTargetException {

			if(super.getTarget() instanceof Hero)
				throw new InvalidTargetException();

			if(actionsAvailable <= 0 )
				throw new NotEnoughActionsException();

			if (!((this instanceof Fighter) && specialAction))
				actionsAvailable--;
			super.attack();


			//if(Game.checkEndTurn()) Game.endTurn();

		}

		public void cure() throws InvalidTargetException, NoAvailableResourcesException, NotEnoughActionsException {
			Character z = getTarget();
			if(!(z instanceof Zombie)) throw new InvalidTargetException("You must select a zombie to cure");
			if(!isAdjacent(this, z)) throw new InvalidTargetException("You must select a close zombie to cure");
			if(getVaccineInventory().isEmpty()) throw new NoAvailableResourcesException("You don't have a Vaccine to preform this action");
			if(getActionsAvailable() == 0) throw new NotEnoughActionsException("No more actions available");

			Point p = z.getLocation();
			Game.zombies.remove(z);
			Game.addHero(p);
			actionsAvailable--;
			//if(Game.checkEndTurn()) Game.endTurn();

		}
		
		public void onCharacterDeath() {
			Point p = this.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
			Game.heroes.remove(this);
			Game.checkGameOver();
			//Game.setVisibility(getLocation(), false);
			Game.updateVisibility();
			//Game.setVisiblityToAllHeroes();
			
		}


	 public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{
		if(getSupplyInventory().isEmpty()) throw new NoAvailableResourcesException("You don't have a supply to use special action");
		(this.getSupplyInventory().get(0)).use(this);
		 setSpecialAction(true);
		 actionsAvailable--;
	 }



	public boolean isSpecialAction() {
		return specialAction;
	}



	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}



	public int getActionsAvailable() {
		return actionsAvailable;
	}



	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}



	public int getMaxActions() {
		return maxActions;
	}



	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}


	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

}
