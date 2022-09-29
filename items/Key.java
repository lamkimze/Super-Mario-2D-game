package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.reset.Resettable;

/**
 * a class indicate the item key
 * @author Lam Kim Ze
 * @version 1
 */
public class Key extends Item {

    /**
     * Constructor
     */
    public Key(){
        super("Key", 'k', true);
        addCapability(Status.END_KEY_HOLDER);

    }
}
