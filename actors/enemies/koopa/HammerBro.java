package game.actors.enemies.koopa;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.actortypes.PatrolingActor;
import game.items.weapons.Hammer;

import java.util.ArrayList;


/**
 * a class indicate an enemy Hammer Bro which attack the player with hammer
 * @author Lam Kim Ze
 * @version 1
 */
public class HammerBro extends KoopaFleet implements PatrolingActor {

    /**
     * class constant
     */
    private static final int HIT_POINT = 230;
    private static final int RESET_MAX_HP_INCREASE = 100;

    /**
     * constructor
     */
    public HammerBro(){
        super("Hammer Bro", '%', HIT_POINT);
        this.addItemToInventory(new Hammer());
        this.addCapability(Status.SHELL);
        this.addCapability(Status.NO_RESTRICTED);
    }

    /**
     * Secondary Constructor for when HammerBro is given PatrolLocations
     * @param partolLocation the patrolLocations for HammerBro
     */
    public HammerBro(ArrayList<Location> partolLocation) {
        super("Hammer Bro", '%', HIT_POINT);
        this.addItemToInventory(new Hammer());
        this.addCapability(Status.NO_RESTRICTED);
        this.addCapability(Status.SHELL);
        this.registerAsPatroller(super.behaviours, partolLocation);
    }

    /**
     * Resets the HammerBro instance, increases the MaxHp
     * @param location the location of the enemy
     */
    @Override
    public void resetTheInstance(Location location) {
        this.increaseMaxHp(RESET_MAX_HP_INCREASE);
        this.removeCapability(Status.RESET);
    }
}
