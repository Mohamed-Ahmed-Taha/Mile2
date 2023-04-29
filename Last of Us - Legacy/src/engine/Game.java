package engine;

import model.characters.*;
import model.characters.Character;
import model.world.*;
import model.collectibles.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
    public static ArrayList<Hero> heroes;
    public static ArrayList<Zombie> zombies;
    public static Cell[][] map;
    
    public static Point randomLocation() {
    	Point p= new Point((int)Math.random()*14,(int)Math.random()*14);
    	
    	while(map[p.x][p.y] != null) {
    		p.x= (int)Math.random()*14;
        	p.y= (int)Math.random()*14;
    	}
    	return p;
    }
    
    public static void startGame(Hero h) {
    	
    	h = availableHeroes.remove((int)Math.random()*8);
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
    	
//    	if(vaxUsed == 5 && heroes.size() >= 5)
//    		return true;
//    	
//    	else 
    		return false;
    }
    
    public static boolean checkGameOver() {
//    	
//    	if(heroes.size() == 0 || heroes.size() < 5 || vaxUsed != 5)
//    		return true;
//    	
//    	else 
    		return false;
    }
    
    public static boolean isEdge(int x, int y) {
    	if(x < 0 || x > 14 || y < 0 || y> 14) {
    		return true;
    	}
    	else
    		return false;
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
    	if(map[x][y] instanceof CharacterCell && ((CharacterCell)map[x][y]).getCharacter() instanceof Hero) {
    			return true;
    	}
    	
    	else {
    		return false;
    	}
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
    	if(!isEdge(p.x, p.y+1) && checkHero(p.x, p.y+1)) {
    			return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public static void endTurn() {
    	for(int i= 0; i<14; i++) {
    		for(int j= 0; j<14; j++) {
    			map[i][j].setVisible(false);
    		}
    	}
    	
    	for(int i= 0; i<heroes.size(); i++) {
    		setVisibility(heroes.get(i).getLocation());
    	}
    	
    	for(int i= 0; i<zombies.size(); i++) {
    		if(doAttack(zombies.get(i).getLocation()))
    			zombies.get(i).attack();
    	}
    	
    	for(int i= 0; i<heroes.size(); i++) {
    		heroes.get(i).setActionsAvailable(heroes.get(i).getMaxActions());
    		heroes.get(i).setSpecialAction(true);
    		heroes.get(i).setTarget(null);
    	}
    	
    	Point p = randomLocation();
    	Zombie z = new Zombie();
    	map[p.x][p.y] = new CharacterCell(z);
    	zombies.add(z);
    }
    
    public static void loadHeroes(String filePath) throws Exception {
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

}
