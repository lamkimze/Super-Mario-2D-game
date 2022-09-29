package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.buff.fadingbuff.FadingBuffManager;
import game.Status;
import game.items.BuyableItem;

/**
 * a class represent a power star extended from consumable item because power star can be consume and implement
 * BuyableItem class since power star can be buy by an actor
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public class PowerStar extends ConsumableItem implements BuyableItem {
    /**
     * Constants used in this class
     */
    private static final int POWER_STAR_HEAL = 200;
    private static final int POWER_START_BASE_DURATION = 10;

    /**
     * keep track on the remaining time of power star
     */
    private int duration;

    /**
     * initialize the price of power star as final since the price will not change
     */
    private final int POWER_STAR_PRICE = 600;

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.duration = POWER_START_BASE_DURATION;
    }

    /**
     * setter setting the duration of the power star
     * @param duration integer indicates the duration of the power star
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * getter to get the price of the power star
     * @return the cost of the power star
     */
    @Override
    public int getPrice() {
        return this.POWER_STAR_PRICE;
    }

    /**
     * adding the magic power given by power star to the actor
     * @param actor the actor consuming the power star
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.addCapability(Status.INVINCIBLE);
        actor.heal(POWER_STAR_HEAL);
        FadingBuffManager.getInstance().addFadingBuff(actor, Status.INVINCIBLE, POWER_START_BASE_DURATION);
        return actor + " consumed the " + this;
    }

    /**
     * Act as a timer in the game for the power star to disappear after the 10 turns from the ground
     * @param currentLocation The location of the actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (this.duration == 0) {
            currentLocation.removeItem(this);
        } else {
            reduceDuration();
        }
    }

    /**
     * Sct as a timer in the game for the power star to disappear after the 10 turns from the inventory
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation);
        if (this.duration == 0) {
            actor.removeItemFromInventory(this);
        } else {
            reduceDuration();
        }
    }

    /**
     * method that allow actor to buy a power star
     * @param actor actor that buy a power star
     * @return description that the actor had successfully buy a power star
     */
    @Override
    public String buy(Actor actor) {
        this.setDuration(POWER_START_BASE_DURATION);
        actor.addItemToInventory(this);
        return actor + " obtained " + this;
    }

    /**
     * act as a timer to reduce the duration for every turn
     */
    private void reduceDuration(){
        duration -= 1;
    }

    /**
     * printing the details of the powerstar
     * @return a String describing the duration left before powerstar get disappear
     */
    @Override
    public String toString() {
        return "PowerStar" + " (" + this.duration  + " turns left)";
    }
}

