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



public class Game {

	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;

	public static Point randomLocation() {
		Point p= new Point((int)(Math.random()*14),(int)(Math.random()*14));

		while(map[p.x][p.y] != null) {
			p.x= (int)(Math.random()*14);
			p.y= (int)(Math.random()*14);
		}
		return p;
	}

	public static void startGame(Hero h) {
		map = new Cell[15][15];
		h = availableHeroes.remove((int)(Math.random()*8));
		map[0][0]= new CharacterCell(h);
		heroes.add(h);
		map[0][0].setVisible(true);
		map[1][0].setVisible(true);
		map[1][1].setVisible(true);
		map[0][1].setVisible(true);

		for(int n=0;n<5;n++) {
			Point p = randomLocation();
			map[p.x][p.y] = new CollectibleCell(new Vaccine());
			p = randomLocation();
			map[p.x][p.y] = new CollectibleCell(new Supply());
			p = randomLocation();
			map[p.x][p.y] = new TrapCell();

		}

		for(int n=0;n<10;n++) {
			Point p = randomLocation();
			Zombie z = new Zombie();
			map[p.x][p.y] = new CharacterCell(z);
			zombies.add(z);
		}

	}

	public static boolean checkWin() {
		return Vaccine.getVaxUsed() == 5 && heroes.size() >= 5;
	}

	public static boolean checkGameOver() {
		return heroes.size() == 0 || heroes.size() < 5 || Vaccine.getVaxUsed() != 5;
	}

	public static boolean isEdge(int x, int y) {
		return x < 0 || x > 14 || y < 0 || y > 14;
	}

	public static void setVisibility(Point p) {
		map[p.x][p.y].setVisible(true);

		if(!isEdge(p.x-1,p.y))
			map[p.x-1][p.y].setVisible(true);

		if(!isEdge(p.x+1,p.y))
			map[p.x+1][p.y].setVisible(true);

		if(!isEdge(p.x-1,p.y-1))
			map[p.x-1][p.y-1].setVisible(true);

		if(!isEdge(p.x-1,p.y+1))
			map[p.x-1][p.y+1].setVisible(true);

		if(!isEdge(p.x+1,p.y-1))
			map[p.x+1][p.y-1].setVisible(true);

		if(!isEdge(p.x+1,p.y+1))
			map[p.x+1][p.y+1].setVisible(true);

		if(!isEdge(p.x,p.y-1))
			map[p.x][p.y-1].setVisible(true);

		if(!isEdge(p.x,p.y+1))
			map[p.x-1][p.y+1].setVisible(true);

	}

	public static boolean checkHero(int x, int y) {
		return map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() instanceof Hero;
	}

	public static boolean doAttack(Point p) {

		if(!isEdge(p.x-1, p.y) && checkHero(p.x-1, p.y)) {
			return true;
		}
		if(!isEdge(p.x+1, p.y) && checkHero(p.x+1, p.y)) {
			return true;
		}
		if(!isEdge(p.x-1, p.y-1) && checkHero(p.x-1, p.y-1)) {
			return true;
		}
		if(!isEdge(p.x-1, p.y+1) && checkHero(p.x-1, p.y+1)) {
			return true;
		}
		if(!isEdge(p.x+1, p.y-1) && checkHero(p.x+1, p.y-1)) {
			return true;
		}
		if(!isEdge(p.x+1, p.y+1) && checkHero(p.x+1, p.y+1)) {
			return true;
		}
		if(!isEdge(p.x, p.y-1) && checkHero(p.x, p.y-1)) {
			return true;
		}
		return !isEdge(p.x, p.y + 1) && checkHero(p.x, p.y + 1);
	}

	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
		for(int i= 0; i<14; i++) {
			for(int j= 0; j<14; j++) {
				map[i][j].setVisible(false);
			}
		}

		for (Hero value : heroes) {
			setVisibility(value.getLocation());
		}

		for (Zombie zombie : zombies) {
			if (doAttack(zombie.getLocation()))
				zombie.attack();
		}

		for (Hero hero : heroes) {
			hero.setActionsAvailable(hero.getMaxActions());
			hero.setSpecialAction(true);
			hero.setTarget(null);
		}

		Point p = randomLocation();
		Zombie z = new Zombie();
		map[p.x][p.y] = new CharacterCell(z);
		zombies.add(z);
	}

	public static void loadHeroes(String filePath) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null){
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
	public static void addToMap(Character c) {
		Point p = randomLocation();
		map[p.x][p.y] = new CharacterCell(c);
		if(c instanceof Hero)
			setVisibility(p);
		else
			map[p.x][p.y].setVisible(false);
	}


	public static void addToMap(Collectible c) {
		Point p = randomLocation();
		map[p.x][p.y] = new CollectibleCell(c);
		map[p.x][p.y].setVisible(false);
	}

}
