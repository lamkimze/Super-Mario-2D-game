package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface which provides an Object with the ability to be consumed.
 */
public interface Consumable {
    /**
     * Method that determines if the item is currently consumable all Consumable will be consumable unless this
     * method is overridden.
     * @return boolean which represents if the consumable can be consumed
     */
    default boolean canConsume(){
        return true;
    }

    /**
     * an abstract method
     * @param actor actor that consume the consumable
     * @param map the map that the player is consuming the item
     * @return a String that indicates the player is consuming the consumable item in a map
     */
    String consume(Actor actor, GameMap map);

}
