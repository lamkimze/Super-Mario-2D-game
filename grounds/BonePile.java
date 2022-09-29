package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.DryBones;
import game.behaviours.partollocations.RectanglePatrolLocations;

/**
 * Class for the BonePile ground which spawns DryBones
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class BonePile extends Ground {

    /**
     * The DryBones that the class has spawned, can only have spawned one on the map at a time
     */
    private DryBones dryBones = null;
    /**
     * Timer for the spawning of the DryBones
     */
    private int spawnDryBonesTimer = 0;
    /**
     * Constant attribute which determines how many turns are need for DryBones to spawn
     */
    private static final int TURNS_FOR_DRY_BONE_SPAWN = 5;

    /**
     * Constructor.
     */
    public BonePile() {
        super('b');
    }

    /**
     * Method that spawns the Dry Bones
     * @param location the location to spawn DryBones
     */
    public void spawnDryBones(Location location){
        if (!location.containsAnActor()){
            int width = (int) (Math.random() * 2 + 1);
            int height = (int) (Math.random() * 2 + 1);
            DryBones dryBones = new DryBones(new RectanglePatrolLocations(location.x(), location.y(), width, height).generateLocations(location.map()));
            location.addActor(dryBones);
            this.dryBones = dryBones;
        }
    }

    /**
     * Allows the BonePipe to experience time
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // Checks to see if this BonePipe has a DryBones patrolling around it.
        if (!location.map().contains(dryBones)){
            if (spawnDryBonesTimer == TURNS_FOR_DRY_BONE_SPAWN){
                spawnDryBones(location);
                spawnDryBonesTimer = 0;
            }
            spawnDryBonesTimer += 1;
        }
    }
}
