package engine;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.world.*;
import model.collectibles.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Game {

	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;

	public static Point randomLocation() {
		Random r = new Random();
		int x = r.nextInt(15);
		int y = r.nextInt(15);
		Point p= new Point(x,y);

		while((map[p.x][p.y] instanceof CharacterCell &&((CharacterCell) map[p.x][p.y]).getCharacter() != null)
				|| map[p.x][p.y] instanceof TrapCell
				|| map[p.x][p.y] instanceof CollectibleCell)
		{
			p.x= r.nextInt(15);
			p.y= r.nextInt(15);
		}
		return p;
	}

	public static void startGame(Hero h) {
		map = new Cell[15][15];



		for(int i= 0; i < map.length; i++) {
			for(int j= 0; j < map[i].length; j++) {
				map[i][j] = new CharacterCell(null);
			}
		}

		Point start = new Point(0,0);
		availableHeroes.remove(h);
		h.setLocation(start);
		heroes.add(h);
		map[start.x][start.y] = new CharacterCell(h);

		for(int n=0;n<5;n++) {
			addCollToMap(new Supply());
			addCollToMap(new Vaccine());
			Point p = randomLocation();
			map[p.x][p.y] = new TrapCell();

		}

		for(int n=0;n<10;n++) {
			addZombie();
		}
		
		updateVisibility();
	}
	
	public static int vaccineOnMap() {
		int c = 0;
		for(int i = 0 ; i<15 ; i++) {
			for(int j = 0 ; j<15 ; j++) {
				if(map[i][j] instanceof CollectibleCell) {
					if((((CollectibleCell) map[i][j]).getCollectible()) instanceof Vaccine ) {
						c++;
					}
				}
			}
		}
		return c;
	}
	
	public static int vaccineInInventory() {
		int c = 0;
		for(int i = 0 ; i<heroes.size() ; i++) {
			c += (heroes.get(i).getVaccineInventory().size());
			}
		return c;
	}

	public static boolean checkWin() {
		return (vaccineOnMap() == 0 && (vaccineInInventory() == 0 && heroes.size() >= 5));
		
	}

	public static boolean checkGameOver() {
		return (heroes.size() == 0 || (vaccineOnMap() == 0 && vaccineInInventory() == 0));
			
	}

	public static boolean isEdge(int x, int y) {
		return x < 0 || x > 14 || y < 0 || y > 14;
	}

	public static void setVisibility(Point p, boolean visible) {
		map[p.x][p.y].setVisible(visible);

		if (!isEdge(p.x + 1, p.y))  //north
			map[p.x + 1][p.y].setVisible(visible);

		if (!isEdge(p.x - 1, p.y))  //south
			map[p.x - 1][p.y].setVisible(visible);

		if (!isEdge(p.x, p.y - 1))    // west
			map[p.x][p.y - 1].setVisible(visible);

		if (!isEdge(p.x, p.y + 1))  // east
			map[p.x][p.y + 1].setVisible(visible);


		if (!isEdge(p.x - 1, p.y - 1))  // south west
			map[p.x - 1][p.y - 1].setVisible(visible);

		if (!isEdge(p.x - 1, p.y + 1))  //south east
			map[p.x - 1][p.y + 1].setVisible(visible);

		if (!isEdge(p.x + 1, p.y - 1))   // north west
			map[p.x + 1][p.y - 1].setVisible(visible);

		if (!isEdge(p.x + 1, p.y + 1))  // north east
			map[p.x + 1][p.y + 1].setVisible(visible);

	}
		public static void updateVisibility () {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					map[i][j].setVisible(false);
				}
			}

			for (Hero value : heroes) {
				setVisibility(value.getLocation(), true);
			}
		}

		public static boolean checkHero ( int x, int y){
			return (
					(map[x][y] instanceof CharacterCell)
							&&
							(((CharacterCell) map[x][y]).getCharacter() instanceof Hero));
		}

		public static Character getAdjChar (Point p){

			if (!isEdge(p.x - 1, p.y) && checkHero(p.x - 1, p.y)) {
				return ((CharacterCell) map[p.x - 1][p.y]).getCharacter();
			}
			if (!isEdge(p.x + 1, p.y) && checkHero(p.x + 1, p.y)) {
				return ((CharacterCell) map[p.x + 1][p.y]).getCharacter();
			}
			if (!isEdge(p.x - 1, p.y - 1) && checkHero(p.x - 1, p.y - 1)) {
				return ((CharacterCell) map[p.x - 1][p.y - 1]).getCharacter();
			}
			if (!isEdge(p.x - 1, p.y + 1) && checkHero(p.x - 1, p.y + 1)) {
				return ((CharacterCell) map[p.x - 1][p.y + 1]).getCharacter();
			}
			if (!isEdge(p.x + 1, p.y - 1) && checkHero(p.x + 1, p.y - 1)) {
				return ((CharacterCell) map[p.x + 1][p.y - 1]).getCharacter();
			}
			if (!isEdge(p.x + 1, p.y + 1) && checkHero(p.x + 1, p.y + 1)) {
				return ((CharacterCell) map[p.x + 1][p.y + 1]).getCharacter();
			}
			if (!isEdge(p.x, p.y - 1) && checkHero(p.x, p.y - 1)) {
				return ((CharacterCell) map[p.x][p.y - 1]).getCharacter();
			}
			if (!isEdge(p.x, p.y + 1) && checkHero(p.x, p.y + 1)) {
				return ((CharacterCell) map[p.x][p.y + 1]).getCharacter();
			}
			return null;
		}

		public static void endTurn () throws InvalidTargetException, NotEnoughActionsException {

			for (Zombie zombie : zombies) {
				zombie.attack();
			}


			for (Hero hero : heroes) {
				hero.setActionsAvailable(hero.getMaxActions());
				hero.setSpecialAction(false);
				hero.setTarget(null);
			}

			addZombie();

			updateVisibility();
		}


		public static boolean checkEndTurn () {
			for (Hero hero : heroes) {
				if (hero.getActionsAvailable() != 0)
					return false;
			}

			return true;

		}
		public static void loadHeroes (String filePath) throws IOException {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				String[] split = line.split(",");
				switch (split[1]) {
					case "FIGH" -> availableHeroes.add(new Fighter(split[0],
							Integer.parseInt(split[2]),
							Integer.parseInt(split[4]),
							Integer.parseInt(split[3])));
					case "MED" -> availableHeroes.add(new Medic(split[0],
							Integer.parseInt(split[2]),
							Integer.parseInt(split[4]),
							Integer.parseInt(split[3])));
					case "EXP" -> availableHeroes.add(new Explorer(split[0],
							Integer.parseInt(split[2]),
							Integer.parseInt(split[4]),
							Integer.parseInt(split[3])));
				}
				line = br.readLine();

			}
		}


		public static void addCollToMap (Collectible c){
			Point p = randomLocation();
			map[p.x][p.y] = new CollectibleCell(c);
		}


		public static void addZombie () {
			Zombie z = new Zombie();
			Point p = randomLocation();
			z.setLocation(p);
			zombies.add(z);
			map[p.x][p.y] = new CharacterCell(z);
		}


		public static void addHero (Point p){
			Hero h = Game.availableHeroes.remove((int) (Math.random() * availableHeroes.size()));
			h.setLocation(p);
			heroes.add(h);
			map[p.x][p.y] = new CharacterCell(h);
//			if(visible) setVisibility(p, true);
		}


	}
