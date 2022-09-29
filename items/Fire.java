package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Fire class which extend from the item class that indicate the weapon of Bowser to attack
 * player and the fire will stay on the ground
 * @author Lam Kim Ze, 31860346
 * @version 1.0
 */
public class Fire extends Item {

    /**
     * keep track on the remaining time of fire
     */
    private int duration;

    /**
     * initialize the time of fire that appear on the map
     */
    private static final int FIRE_STAY_TIME = 3;

    /**
     * constructor
     */
    public Fire(){
        super("Fire", 'f', false);
        this.duration = FIRE_STAY_TIME;
    }

    /**
     * Act as a timer in the game for the fire to disappear after 3 turns
     * @param currentLocation The location of the fire on the ground.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(this.duration == 0){
            currentLocation.removeItem(this);
        }
        else{
            if(currentLocation.containsAnActor()){
                currentLocation.getActor().hurt(20);
            }
            reduceDuration();
        }

    }

    /**
     * reduce the duration of fire on the ground
     */
    private void reduceDuration(){
        duration -= 1;
    }

    /**
     * printing the details of the fire
     * @return the description of fire remaining on the ground
     */
    public String toString(){
        return "Fire on the ground remains " + duration + " turns.";
    }
}
