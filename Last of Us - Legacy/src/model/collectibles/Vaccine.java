package model.collectibles;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Hero;
import model.characters.Zombie;

import java.awt.*;

public class Vaccine implements Collectible {
	private static int vaxUsed;



	public Vaccine() {
		
	}

	public void pickUp(Hero h) {

		h.getVaccineInventory().add(this);
	}

	public void use(Hero h) {
		vaxUsed++;
		Character z = h.getTarget();
		h.getVaccineInventory().remove(this);
		Game.zombies.remove(z);
		h.setTarget(null);
		Point p = z.getLocation();
		Game.addHero(p);
	}

	public static void setVaxUsed(int vaxUsed) {
		Vaccine.vaxUsed = vaxUsed;
	}

	public static int getVaxUsed() {
		return vaxUsed;
	}

}
