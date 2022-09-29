package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents bare dirt.
 * @author Clinton Nguyen, 31500145
 * @version 1
 */
public class Dirt extends Ground {

	/**
	 * constructor
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE);
		this.addCapability(Status.LAVA_REPLACABLE);
	}

	/**
	 * Returns String "Dirt"
	 * @return String "Dirt"
	 */
	public String toString(){
		return "Dirt";
	}

}
