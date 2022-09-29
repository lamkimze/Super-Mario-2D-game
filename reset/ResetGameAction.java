package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * An action class which allows for the resetting of the Game
 * @author Rory Tobin-Underwood, 31452434
 * @version 1
 */
public class ResetGameAction extends Action {

    /**
     * Perform the Action, which is to run the reset all the resettable instances
     * @param actor actor who resets
     * @param map The map the actor is on.
     * @return String which describes the action taken
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        actor.removeCapability(Status.CAN_RESET);
        return this.menuDescription(actor);
    }

    /**
     * Returns a descriptive string.
     * @param actor The actor performing the action.
     * @return String which describes the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " reset the game";
    }

    /**
     * Returns the hotkey for the resetting action
     * @return the char "r".
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
