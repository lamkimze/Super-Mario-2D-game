package game.items.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.grounds.Dirt;

import java.util.ArrayList;
import java.util.List;

/**
 * Hammer class which indicates that the weapon for Hammer Bro to attack player
 * and the weapon will stay on the position of player. The player can pick up the
 * hammer and use it to attack enemy once.
 * @author Lam Kim Ze
 * @version 1.0
 */
public class Hammer extends WeaponItem {

    /**
     * Constructor
     */
    private static final int DAMAGE = 80;
    private static final int HITRATE = 70;
    private static final int HAMMER_STAY_TIME = 5;
    private int duration;

    /**
     * Constructor.
     */
    public Hammer() {
        super("Hammer", 'h', DAMAGE, "smash", HITRATE);
        this.duration = HAMMER_STAY_TIME;
        this.addCapability(Status.SHELL_BREAKER);
    }

    /**
     * Act as a timer in the game for the Hammer to disappear after 2 turn and make the ground become dirt
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(this.duration == 0){
            currentLocation.removeItem(this);
        }
        else{
            reduceDuration();
        }
    }

    /**
     * reduce the duration by one
     */
    private void reduceDuration(){
        duration -= 1;
    }


}
