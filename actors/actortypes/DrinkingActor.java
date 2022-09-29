package game.actors.actortypes;

import game.behaviours.Behaviour;
import game.behaviours.DrinkBehaviour;

import java.util.Map;

/**
 * Interface for providing Actors with the Patrolling Behaviour
 * @author Rory-Tobin Underwood
 * @version 1
 */
public interface DrinkingActor {

    /**
     * Default method that provides the Actor with the AttackBehaviour
     * @param behaviours the behaviour Map of the Actor
     */
    default void registerAsDrinker(Map<Integer, Behaviour> behaviours){
        behaviours.put(4, new DrinkBehaviour());
    }
}
