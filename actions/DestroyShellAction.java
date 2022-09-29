package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * an action class which represent destroying the action extended from the action class because it is an action
 * @author Clinton Nguyen, 3150145
 * @version 1
 */

public class DestroyShellAction extends Action {

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
     *
     * @param target the Actor to attack
     * @param direction direction of the target
     */
    public DestroyShellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Execute method to determine what happens when shell is destroyed.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns string method of what happens when shell is destroyed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

            Weapon weapon = actor.getWeapon();

            if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                return actor + " misses " + target + ".";
            }

            int damage = weapon.damage();
            String result = actor + " " + weapon.verb() + " " + target + "'s shell.";
            target.hurt(damage);
            if (!target.isConscious()) {
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction(actor));
                for (Action drop : dropActions)
                    drop.execute(target, map);
                // remove actor
                map.removeActor(target);
                result += System.lineSeparator() + target + " is killed.";
            }
            return result;
        }


    /**
     * Menu description of Destroying shell action.
     * @param actor The actor performing the action.
     * @return String a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Mario attacks " + target + "'s shell ";
    }
}
