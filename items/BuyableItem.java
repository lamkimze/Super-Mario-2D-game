package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for items which are Buyable.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1
 */
public interface BuyableItem {
    /**
     * Abstract method that returns the price of the Buyable item
     * @return int that represents the price
     */
    int getPrice();

    /**
     * Abstract method that preforms processes relating to buying the item
     * @param actor actor that buy the item
     * @return describing the actor had successfully buy the item
     */
    String buy(Actor actor);
}
