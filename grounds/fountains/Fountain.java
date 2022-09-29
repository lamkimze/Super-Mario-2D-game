package game.grounds.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;
import game.actions.RefillAction;
import game.fillablesystem.Fillable;
import game.fillablesystem.Filler;
import game.items.consumables.Consumable;

/**
 * Class which contains methods and attributes for the Fountain Ground object.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public abstract class Fountain extends Ground implements Consumable, Filler {
    /**
     * Attribute which is the maximum capacity of the fountain
     */
    private static final int MAX_FOUNTAIN_CAPACITY = 10;
    /**
     * Attribute which is how much water is used when refilling
     */
    private static final int FOUNTAIN_WATER_CAPACITY_USAGE = 2;
    /**
     * Attribute which is the current capacity of the fountain
     */
    private int currentWaterCapacity = MAX_FOUNTAIN_CAPACITY;
    /**
     * Attribute which is the name of the fountain
     */
    private String name;
    /**
     * Attribute which is how long the fountain has been empty for
     */
    private int replenishTimer = 0;

    /**
     * Constructor
     * @param displayChar the display character for the fountain
     * @param name the name of the fountain
     */
    public Fountain(char displayChar, String name) {
        super(displayChar);
        this.addCapability(Status.DRINKABLE);
        this.name = name;
    }

    /**
     * Returns allowable actions an actor can undertake for the fountain
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an ActionList which contains all the allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // if actor is standing on the fountain
        ActionList actionList = new ActionList();
        if (direction.equals("") && actor.hasCapability(Status.FILLABLE_CONTAINER) && canFillUp()){
            // Optimally would have rather made a function in the actor class similar to getWeapon() but cannot edit engine
            for (Item item: actor.getInventory()) {
                if (item instanceof Fillable) {
                    actionList.add(new RefillAction(this,  (Fillable) item));
                }
            }
        }
        else if (direction.equals("") && actor.hasCapability(Status.DRINKER) && canConsume()){
            actionList.add(new ConsumeAction(this));
        }
        return actionList;
    }

    /**
     * Allows the fountain to replenish after 5 turns of being empty
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.currentWaterCapacity == 0){
            this.replenishTimer += 1;
            if (this.replenishTimer == 5){
                this.currentWaterCapacity = MAX_FOUNTAIN_CAPACITY;
                this.replenishTimer = 0;
            }
        }
        super.tick(location);
    }

    /**
     * Method that reduces the current capacity of the fountain
     * @param amount amount to reduce the current capacity by
     */
    public void reduceCurrentCapacity(int amount){
        this.currentWaterCapacity -= amount;
    }

    /**
     * Method to check if the fountain has enough capacity to fill up
     * @return Boolean which is True if can fill, False otherwise
     */
    public boolean canFillUp() {
        return this.currentWaterCapacity >= 2;
    }

    /**
     * Method to check if the fountain has enough capacity to be consumed from
     * @return Boolean which is True if can consume, False otherwise
     */
    @Override
    public boolean canConsume(){
        return this.currentWaterCapacity >= 4;
    }

    /**
     * Performs the consummation of the consumable
     * @param actor actor that consume the consumable
     * @param map map that the actor is on
     * @return Descriptive string of the consummation
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        this.reduceCurrentCapacity(4);
        return createNewFillableCharge().consume(actor, map) + " from " + this;
    }

    /**
     * Creates a descriptive string of the fountain
     * @return descriptive string of the fountain
     */
    public String toString(){
        return this.name + " (" + this.currentWaterCapacity + "/" + MAX_FOUNTAIN_CAPACITY + ")";
    }

    /**
     * Filler method that reduces the capacity of the fountain
     */
    public void fillReduceCapacityAmount(){
       this.reduceCurrentCapacity(FOUNTAIN_WATER_CAPACITY_USAGE);
    }
}
