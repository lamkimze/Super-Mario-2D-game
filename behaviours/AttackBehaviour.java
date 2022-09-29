package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.items.Fire;

/**
 * An action class that indicates attacking behaviour which attacking another actor in a certain regular pattern
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */

public class AttackBehaviour implements Behaviour {

    /**
     * creating an instance of actor which represent the target actor that is going to be attacked
     */
    private final Actor target;

    /**
     * constructor
     * @param target the actor being attack
     */
    public AttackBehaviour(Actor target) {
        this.target = target;
    }


    /**
     * this method is used to attack the player according to a certain rules
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the attack action on the targeted actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(this.target);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there) {
                if (!actor.hasCapability(Status.AGGROED)){
                    actor.addCapability(Status.AGGROED);
                }
                return new AttackAction(this.target, exit.getName());
            }
        }

        return null;
    }
}