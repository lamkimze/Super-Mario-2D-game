package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Fire;
import game.items.Key;
import game.items.weapons.Hammer;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();


	/**
	 * Constructor.
	 * @param target the Actor to attack
	 * @param direction String which represents the attack direction
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Action, which is to attack the target Actor.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return String of what happens after attack action.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		String result = "";
		if (!(rand.nextInt(100) <= weapon.chanceToHit())){
			return actor + " misses " + target + ".";
		}
		else if (actor.hasCapability(Status.INVINCIBLE)){
			this.dropItems(actor, map);
			map.removeActor(target);
			return System.lineSeparator() + target + " is killed.";
		}
		else if (target.hasCapability(Status.INVINCIBLE)){
			return actor + " hits " + target + " but " + target + " is invincible";
		}
		else {
			int damage = weapon.damage();
			result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if(actor.hasCapability(Status.FIRE_ATTACK)){
				map.locationOf(target).addItem(new Fire());
			}
			if (!target.isConscious()) {
				this.dropItems(actor, map);
				if (!target.hasCapability(Status.SHELL)){
					if(target.hasCapability(Status.TELE_GUARD)){
						actor.addCapability(Status.TELEPORTABLE);
					}
					map.removeActor(target);
					result += System.lineSeparator() + target + " is killed.";
				}
				else{
					result += System.lineSeparator() + target + " becomes dormant.";
					target.addCapability(Status.DORMANT);
				}
			}
		}

		return result;
	}

	/**
	 * Method that allow actor to drop weapon to the ground
	 * @param actor actor who drop the item
	 * @param map the game map that is executing
	 */
	protected void dropItems(Actor actor, GameMap map){
		ActionList dropActions = new ActionList();
		// drop all items
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction(actor));
		for (Action drop : dropActions)
			drop.execute(target, map);
	}


	/**
	 * Returns a descriptive string for the actor attacking the target
	 * @param actor The actor performing the action.
	 * @return descriptive string
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
