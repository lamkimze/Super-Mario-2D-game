package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.fillablesystem.Fillable;

/**
 * Class for the Bottle item which is a ConsumableItem and Fillable
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class Bottle extends ConsumableItem implements Fillable {
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.addCapability(Status.FILLABLE_CONTAINER);
        this.addCapability(Status.PERMANENT);
    }

    /**
     * Performs the consummation
     * @param actor the actor who is consuming
     * @param map the map the actor is on
     * @return a Descriptive string of the consummation
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        super.consume(actor, map);
        return this.getTopFillableCharge().consume(actor, map);
    }

    /**
     * Returns a boolean which represents if the item can be consumed
     * @return True if can consume, False otherwise
     */
    @Override
    public boolean canConsume() {
        return !chargeStack.isEmpty();
    }

    /**
     * Returns a Descriptive string for the bottle
     * @return String a descriptive string
     */
    @Override
    public String toString() {
        return "Bottle" + chargeStack;
    }
}
