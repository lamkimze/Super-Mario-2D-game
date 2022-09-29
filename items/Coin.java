package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.PickUpCoinAction;
import game.reset.Resettable;


/**
 * A class that represent coin extended from item class as coin is considered as an item and implementing resettable class
 * because coin can be reset if the player reset the game
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */

public class Coin extends Item implements Resettable {

    /**
     * value of the coin
     */
    private int value;
    /***
     * Constructor.
     * @param value value of the coin
     */
    public Coin(Integer value) {
        super("Coin", '$', true);
        this.value = value;
        registerInstance();
    }

    /**
     * getter that give the value of the coin
     * @return the value of the coin
     */
    public int getValue(){
        return this.value;
    }

    /**
     * adding reset status to the coin class
     */
    @Override
    public void setToResetInstance() {
        addCapability(Status.RESET);
    }

    @Override
    public void resetTheInstance(Location location) {
        location.removeItem(this);
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        if (hasCapability(Status.RESET)){
            this.resetTheInstance(currentLocation);
        }
    }

    /**
     * method that allow actor to pick up the coin
     * @param actor actor that pick up the coin
     * @return the action picking up the coin
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    /**
     * method describing the coin
     * @return a string describing the coin and its value
     */
    @Override
    public String toString() {
        return "Coin($" + this.value + ")";
    }
}
