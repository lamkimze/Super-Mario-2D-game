package game.actors.actortypes;

import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Behaviour;
import game.behaviours.PatrolBehaviour;

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for providing Actors with the Patrolling Behaviour
 * @author Rory-Tobin Underwood
 * @version 1
 */
public interface PatrolingActor {

    /**
     * Default method that provides the Actor with the PatrolBehaviour
     * @param behaviours the behaviour Map of the Actor
     * @param partolLocations the Locations that the Actor will patrol
     */
    default void registerAsPatroller(Map<Integer, Behaviour> behaviours, ArrayList<Location> partolLocations){
        behaviours.put(3, new PatrolBehaviour(partolLocations));
    }
}
