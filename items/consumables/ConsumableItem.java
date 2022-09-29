package game.items.consumables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * an abstract class that represent consumable item
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public abstract class ConsumableItem extends Item implements Consumable{

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * adding consumable action into a list which player can use and return the action list
     * @return a list of action that player can execute
     */
    @Override
    public List<Action> getAllowableActions() {
        ArrayList<Action> actionList = new ArrayList<>();
        if (this.canConsume()){
            actionList.add(new ConsumeAction(this));
        }
        return actionList;
    }

    /**
     * Performs the consummation of the Consumable (removes if not permanent item)
     * @param actor actor that consume the consumable
     * @param map the map the actor is on
     * @return a descriptive string
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        if (!this.hasCapability(Status.PERMANENT)) {
            if (map.locationOf(actor).getItems().contains(this)) {
                map.locationOf(actor).removeItem(this);
            } else {
                actor.removeItemFromInventory(this);
            }
        }
        return null;
    }
}
