package game.actors.actortypes;

import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

import java.util.Map;

/**
 * Interface for providing Actors with the Patrolling Behaviour
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public interface WanderableActor {

    /**
     * Default method that provides the Actor with the AttackBehaviour
     * @param behaviours the behaviour Map of the Actor
     */
    default void registerAsWanderable(Map<Integer, Behaviour> behaviours){
        behaviours.put(5, new WanderBehaviour());
    }

}
