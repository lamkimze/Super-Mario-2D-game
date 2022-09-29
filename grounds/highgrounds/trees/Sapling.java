package game.grounds.highgrounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.items.Coin;
import static java.lang.Math.*;
import java.util.Random;

/**
 * The Sapling class extends from Tree and is one of three stages of a tree.
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */

public class Sapling extends Tree{
    /**
     * Constants used in this class
     */
    private static final int COIN_SPAWN_RATE = 10;
    private static final int TURNS_REQUIRED_TO_GROW = 10;
    private static final int SAPLING_JUMP_SUCCESS_RATE = 80;
    private static final int SAPLING_JUMP_DAMAGE = 20;
    private static final int SAPLING_SPAWN_COIN_VALUE = 20;

    /**
     * Constructor of sapling.
     */
    public Sapling() {
        super('t');
        super.jumpDamage = SAPLING_JUMP_DAMAGE;
        super.jumpSuccessRate = SAPLING_JUMP_SUCCESS_RATE;
    }

    /**
     * Spawns coin at a given location.
     * @param location Location instance where the coin is to be spawned.
     */
    public void spawnCoin(Location location){
        Coin coin = new Coin(SAPLING_SPAWN_COIN_VALUE);
        location.addItem(coin);
    }

    /**
     * Spawns mature at a given location.
     * @param location Location instance where the mature is to be spawned.
     */
    private void spawnMature(Location location) {
        Mature mature = new Mature();
        location.setGround(mature);
    }


    @Override
    /**
     * Sapling can experience the joys of time.
     * @param location Location instance of the Sapling tree.
     */
    public void tick(Location location) {
        int num = (int) (Math.random() * 100);
        super.tick(location);
        if (!this.hasCapability(Status.CONVERTED_TO_DIRT)){
            // 10% chance to spawn coin
            if (num < COIN_SPAWN_RATE) {
                this.spawnCoin(location);
            }

            this.increaseAge();
            // takes 10 turns to grow into Mature
            if (this.getAge() % TURNS_REQUIRED_TO_GROW == 0){
                this.spawnMature(location);
            }

        }

    }

    /**
     * String method to return Sapling as a string.
     * @return returns Sapling as a string.
     */
    public String toString(){
        return "Sapling";
    }

}
