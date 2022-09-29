package game.actors.actortypes;

import game.actors.player.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

import java.util.Map;

/**
 * Interface for providing Actors with the Attacking Behaviour
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public interface AttackingActor {

    /**
     * Default method that provides the Actor with the AttackBehaviour
     * @param behaviours the behaviour Map of the Actor
     */
    default void registerAsAttacker(Map<Integer, Behaviour> behaviours){
        behaviours.put(1, new AttackBehaviour(Player.getInstance()));
    }
}
