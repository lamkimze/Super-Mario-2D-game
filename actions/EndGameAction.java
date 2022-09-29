package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action class which indicates end game action which the player successfully complete the game.
 * @author Lam Kim Ze
 * @version 1.0
 */
public class EndGameAction extends Action {


    /**
     * execute the end game action which rescue the princess peach and finish the game
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing that the actor complete the game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Good work on completing the game!";
    }

    /**
     * a description that the actor save princess peach.
     * @param actor The actor performing the action.
     * @return describing the action saving Princess Peach.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Save Peach and finish the game!";
    }
}
