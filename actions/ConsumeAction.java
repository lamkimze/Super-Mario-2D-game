package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Consumable;

/**
 *  * An action class which allows for consumption of a consumable
 * @author Lam Kim Ze, 31860346
 * @version 3.0
 */
public class ConsumeAction extends Action {

    private final Consumable consumable;

    /**
     * Constructor.
     * @param consumable the Consumable which is be consumed (could be an item or another)
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Perform the consumption action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return returns string of what happens after consumption.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        return consumable.consume(actor, map);
    }

    /**
     * Menu description of consume action.
     * @param actor The actor performing the action.
     * @return a string describing the actor consume the consumable item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumable;
    }
}
