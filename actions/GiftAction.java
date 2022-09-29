package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * An action class that handles the gifting of Items
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class GiftAction extends Action {

    /**
     * The actor that will gift the item
     */
    private Actor gifter;
    /**
     * The item that will be gifted
     */
    private Item gift;

    /**
     * Constructor
     * @param gifter the actor that will gift
     * @param gift the item to be gifted
     */
    public GiftAction(Actor gifter, Item gift){
        this.gifter = gifter;
        this.gift = gift;
    }

    /**
     * Executes the gift action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String a descriptive string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(gift);
        gifter.removeCapability(Status.CAN_GIFT);
        return actor + " received a " + gift + " as a gift";
    }

    /**
     * Returns a descriptive string for the Menu to display
     * @param actor The actor performing the action.
     * @return String a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Talk to " + gifter + " for a gift";
    }
}
