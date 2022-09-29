package game.items.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.items.BuyableItem;

/**
 * A class that represent wrench extended from WeaponItem class because it is a weapon and implement BuyableItem as an
 * actor can buy a wrench
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */
public class Wrench extends WeaponItem implements BuyableItem {



    /**
     * Constants used in this class
     */
    private static final int WRENCH_PRICE = 200;
    private static final int WRENCH_DAMAGE = 50;
    private static final int WRENCH_HIT_RATE = 80;

    /**
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'W', WRENCH_DAMAGE, "bashes", WRENCH_HIT_RATE);
        this.addCapability(Status.SHELL_BREAKER);
    }

    /**
     * getter to get the price of wrench
     * @return the price of wrench which is 200
     */
    @Override
    public int getPrice() {
        return this.WRENCH_PRICE;
    }

    /**
     * method that allow actor to buy a wrench
     * @param actor actor that buy a wrench
     * @return a string describing the actor had successfully buying a wrench
     */
    @Override
    public String buy(Actor actor) {
        actor.addItemToInventory(this);
        return actor + " obtained " + this;
    }
}
