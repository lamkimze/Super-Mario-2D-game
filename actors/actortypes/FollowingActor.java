package game.actors.actortypes;

import game.actors.player.Player;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;


import java.util.Map;

/**
 * Interface for providing Actors with the Patrolling Behaviour
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public interface FollowingActor {

    /**
     * Default method that provides the Actor with the AttackBehaviour
     * @param behaviours the behaviour Map of the Actor
     */
    default void registerAsFollower(Map<Integer, Behaviour> behaviours){
        behaviours.put(2, new FollowBehaviour(Player.getInstance()));
    }
}
