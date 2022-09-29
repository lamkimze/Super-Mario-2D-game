package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;

/**
 * A class that extended from high ground which indicates a wall
 * @author Lam Kim Ze, 31860346
 * @version 3.0
 */
public class Wall extends HighGround {
	/**
	 * Constants used in this class
	 */
	private static final int WALL_JUMP_SUCCESS_RATE = 80;
	private static final int WALL_JUMP_DAMAGE = 20;

	/**
	 * constructor initializing the char represent wall, jumping success rate and damage received due to fail to jump to
	 * the wall
	 */
	public Wall() {
		super('#');
		super.jumpSuccessRate = WALL_JUMP_SUCCESS_RATE;
		super.jumpDamage = WALL_JUMP_DAMAGE;

	}

	/**
	 * method that indicates that the actor cannot walk through the wall
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor.hasCapability(Status.FLY) || actor.hasCapability(Status.NO_RESTRICTED)){
			return true;
		}
		return false;
	}

	/**
	 * methods that a terrain that blocks thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * giving the name 'wall'
	 * @return the fullname 'wall'
	 */
	public String toString(){
		return "Wall";
	}
}
