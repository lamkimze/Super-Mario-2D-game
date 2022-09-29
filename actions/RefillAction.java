package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.fillablesystem.Fillable;
import game.fillablesystem.Filler;

/**
 * An action class that handles the refilling of Fillable obhjects
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class RefillAction extends Action {


    /**
     * Attribute which is the Filler the Actor is filling up from
     */
    private Filler filler;

    /**
     * Attribute which is the Fillable that will be refilled
     */
    private Fillable fillable;

    /**
     * Constructor
     * @param filler the Filler the Actor will fill up from
     * @param fillable the Fillable object that will be filled
     */
    public RefillAction(Filler filler, Fillable fillable) {
        this.filler = filler;
        this.fillable = fillable;
    }

    /**
     * Executes the Refill Action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return descriptive string which describes the action taken
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        filler.fill(fillable);
        return actor + " refilled from " + this.filler;
    }

    /**
     * The console menu description
     * @param actor The actor performing the action.
     * @return a String to be displayed in the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refill from " + this.filler;
    }
}
