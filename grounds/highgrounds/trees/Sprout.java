package game.grounds.highgrounds.trees;


import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.enemies.Goomba;
import game.grounds.Dirt;
import static java.lang.Math.*;
import java.util.Random;

/**
 * The Sprout class extends from Tree and is one of three stages of a tree.
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */
public class Sprout extends Tree{
    /**
     * Constants used in this class
     */
    private static final int GOOMBA_SPAWN_RATE = 10;
    private static final int TURNS_REQUIRED_TO_GROW = 10;
    private static final int SPROUT_JUMP_SUCCESS_RATE = 90;
    private static final int SPROUT_JUMP_DAMAGE = 10;

    /**
     * Constructor of Sprout.
     */
    public Sprout() {
        super('+');
        super.jumpDamage = SPROUT_JUMP_DAMAGE;
        super.jumpSuccessRate = SPROUT_JUMP_SUCCESS_RATE;
    }

    /**
     * Spawns Goomba at a given location.
     * @param location Location instance where the Goomba is to be spawned.
     */
    public void spawnGoomba(Location location){
        Goomba goomba = new Goomba();
        location.addActor(goomba);
    }

    /**
     * Spawns Sapling at a given location.
     * @param location Location instance where the Sapling is to be spawned.
     */
    private void spawnSapling(Location location) {
        Sapling sapling = new Sapling();
        location.setGround(sapling);
    }


    @Override
    /**
     * Sprout can experience the joys of time.
     * @param location Location instance of the Sprout tree.
     */
    public void tick(Location location) {
        int num = (int) (Math.random() * 100);
        super.tick(location);
        if (!this.hasCapability(Status.CONVERTED_TO_DIRT)){
            // spawn Goomba 10% chance, also if actor stands on it, cannot spawn Goomba
            if (!location.containsAnActor()) {
                if (num < GOOMBA_SPAWN_RATE){
                    this.spawnGoomba(location);
                }
            }
            this.increaseAge();
            // takes 10 turns to grow into small tree/sapling
            if (this.getAge() % TURNS_REQUIRED_TO_GROW == 0){
                this.spawnSapling(location);
            }
        }

    }


    /**
     * String method to return Sprout as a string.
     * @return returns Sprout as a string.
     */
    public String toString(){
        return "Sprout";
    }
}
