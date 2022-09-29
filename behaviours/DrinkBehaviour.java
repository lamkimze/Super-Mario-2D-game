package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Behaviour class for Actors to drink directly from objects like fountains.
 * @author Rory-Tobin Underwood
 * @version 1
 */
public class DrinkBehaviour implements Behaviour {
    /**
     * Gets the action the actor can take
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action for the Actor to take or null if no appropriate Action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        if (actorLocation.getGround().hasCapability(Status.DRINKABLE)){
            ActionList actionList = actorLocation.getGround().allowableActions(actor, actorLocation, "");
            if (actionList.size()>0){
                return actionList.get(0);
            }
        }
        return null;
    }
}
