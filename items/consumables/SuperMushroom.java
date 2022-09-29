package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.BuyableItem;

/**
 * A class that represent the Super Mushroom. This class extends ConsumableItem class because it can be consumed by an actor and
 * implements BuyableItem class because it is sold by Toad.
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public class SuperMushroom extends ConsumableItem implements BuyableItem {
    private static final int INCREASED_AMOUNT_OF_MAX_HP = 50;

    /**
     * initialized the price of super mushroom as final since the price will not change
     */
    private final int SUPER_MUSHROOM_PRICE = 400; // Price of all SuperMushrooms are 400
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
    }

    /**
     * get the cost of the super mushroom
     * @return the price of super mushroom
     */
    @Override
    public int getPrice() {
        return SUPER_MUSHROOM_PRICE;
    }

    /**
     * adding the magic power given by super mushroom to the actor
     * @param actor the actor consuming the super mushroom
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        super.consume(actor, map);
        actor.addCapability(Status.TALL);
        actor.increaseMaxHp(INCREASED_AMOUNT_OF_MAX_HP);
        return actor + " consumed the " + this;
    }

    /**
     * method that allow actor to buy a super mushroom
     * @param actor actor that buy super mushroom
     * @return a string describing that the actor had successfully buy a super mushroom
     */
    @Override
    public String buy(Actor actor) {
        actor.addItemToInventory(this);
        return actor + " obtained " + this;
    }
}
