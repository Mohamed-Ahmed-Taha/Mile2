package model.characters;

import java.awt.*;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
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



	public boolean clear(Cell c){
		if(c instanceof CharacterCell)
			return false;

		if(c instanceof TrapCell)
			super.setCurrentHp(super.getCurrentHp()-((TrapCell) c).getTrapDamage());

		if(c instanceof CollectibleCell){
			if(((CollectibleCell) c).getCollectible() instanceof Supply)
				supplyInventory.add(new Supply());

			if(((CollectibleCell) c).getCollectible() instanceof Vaccine)
				vaccineInventory.add(new Vaccine());
		}


		return true;
	}

	public Point newCoord(int x, int y, Direction d){
			Point n = switch (d) {
				case UP -> new Point(x, y + 1);
				case DOWN -> new Point(x, y - 1);
				case LEFT -> new Point(x - 1, y);
				case RIGHT -> new Point(x + 1, y);
			};

		return n;
	}

	public void move(Direction d) throws MovementException, InvalidTargetException, NotEnoughActionsException {
		Point loc = super.getLocation();
		Point n = newCoord(loc.x,loc.y,d);

		if(Game.isEdge(n.x, n.y)) throw new MovementException(); 

		if(d == Direction.UP && clear(Game.map[loc.x][loc.y +1])) {
			super.setLocation(n);
			Game.map[loc.x + 1][loc.y + 2].setVisible(true);
			Game.map[loc.x][loc.y + 2].setVisible(true);
			Game.map[loc.x - 1][loc.y + 2].setVisible(true);
		}

		if(d == Direction.LEFT && clear(Game.map[loc.x -1][loc.y])) {
			super.setLocation(n);
			Game.map[loc.x - 2][loc.y].setVisible(true);
			Game.map[loc.x - 2][loc.y - 1].setVisible(true);
			Game.map[loc.x - 2][loc.y + 1].setVisible(true);
		}
		if(d == Direction.DOWN && clear(Game.map[loc.x][loc.y -1])) {
			super.setLocation(n);
			Game.map[loc.x + 1][loc.y - 2].setVisible(true);
			Game.map[loc.x][loc.y - 2].setVisible(true);
			Game.map[loc.x - 1][loc.y - 2].setVisible(true);
		}

		if(d == Direction.RIGHT && clear(Game.map[loc.x + 1][loc.y])){
			super.setLocation(n);
			Game.map[loc.x + 2][loc.y].setVisible(true);
			Game.map[loc.x + 2][loc.y - 1].setVisible(true);
			Game.map[loc.x + 2][loc.y + 1].setVisible(true);}

		actionsAvailable--;
		if(actionsAvailable == 0)  Game.endTurn();
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


		public void attack() throws NotEnoughActionsException, InvalidTargetException {
			
			if(super.getTarget() instanceof Hero)
				throw new InvalidTargetException();
			if(actionsAvailable-- <= 0)
				throw new NotEnoughActionsException();
		
			super.attack();
			if(actionsAvailable == 0)  Game.endTurn();
		}
		
		public void onCharacterDeath() {
			
			Game.heroes.remove(this);
			super.onCharacterDeath();
			
			
		}
	
}
