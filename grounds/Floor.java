package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class extended from ground class that represents the floor inside a building.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1
 */
public class Floor extends Ground {

	/**
	 * constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * method that restrict the enemy to walk on the floor but allow player to walk on it
	 * @param actor the Actor to check
	 * @return true if the actor is player and false if the actor is enemy
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.FLOOR_RESTRICTED);
	}
}
