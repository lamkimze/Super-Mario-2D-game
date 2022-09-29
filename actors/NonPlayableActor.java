package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * a class indicates the non-playable character
 * @author Rory-Tobin Underwood
 * @version 2.0
 */
public abstract class NonPlayableActor extends Actor {
    /**
     * a hash map stores the behaviour of the non-playable actor
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NonPlayableActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Method which gets an Action based on the Behaviours the Enemy has.
     * @param map the GameMap the enemy is on
     * @return an Action for that the Enemy will execute
     */
    public Action getActionFromBehaviour(GameMap map){
        for(Behaviour behaviour : behaviours.values()){
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

}
