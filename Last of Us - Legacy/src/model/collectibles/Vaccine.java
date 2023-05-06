package model.collectibles;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;

public class Vaccine implements Collectible {
	private static int vaxUsed;



	public Vaccine() {
		
	}

	public void pickUp(Hero h) {

		h.getVaccineInventory().add(this);
	}

	public void use(Hero h) {
		vaxUsed++;
		h.getVaccineInventory().remove(this);
	}

	public static void setVaxUsed(int vaxUsed) {
		Vaccine.vaxUsed = vaxUsed;
	}

	public static int getVaxUsed() {
		return vaxUsed;
	}

}
