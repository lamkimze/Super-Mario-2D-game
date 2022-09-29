package game.grounds.highgrounds.trees;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.enemies.koopa.FlyingKoopa;
import game.actors.enemies.koopa.Koopa;
import game.grounds.Dirt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Mature class extends from Tree and is one of three stages of a tree.
 * @author Clinton Nguyen, 31500145.
 * @version 1
 */
public class Mature extends Tree{
    /**
     * Constants used in this class
     */
    private static final int KOOPA_SPAWN_RATE = 15;
    private static final int WITHER_CHANCE = 20;
    private static final int TURNS_REQUIRED_TO_GROW = 5;
    private static final int MATURE_JUMP_SUCCESS_RATE = 70;
    private static final int MATURE_JUMP_DAMAGE = 30;

    /**
     * Constructor of mature.
     */
    public Mature() {
        super('T');
        super.jumpDamage = MATURE_JUMP_DAMAGE;
        super.jumpSuccessRate = MATURE_JUMP_SUCCESS_RATE;
    }

    /**
     * Spawns Koopa at a given location.
     * @param location Location instance where the Koopa is to be spawned.
     */
    public void spawnKoopa(Location location){
        Koopa koopa = new Koopa();
        location.addActor(koopa);
    }

    /**
     * Spawns Flying Koopa at a given location
     * @param location Location instance where the Flying Koopa is to be spawned.
     */
    public void spawnFlyingKoopa(Location location){
        FlyingKoopa flyingKoopa = new FlyingKoopa();
        location.addActor(flyingKoopa);
    }

    /**
     * Grows Sprout at a given location.
     * @param location Location instance where the Sprout is to be spawned.
     */
    private void growSprout(Location location) {

        Sprout sprout = new Sprout();
        ArrayList<Location> locationsList = new ArrayList<>();
        List<Exit> exitList = location.getExits();


        for (int i = 0; i < exitList.size(); i ++){
            Location locationExit = exitList.get(i).getDestination();
            if (locationExit.getGround().getDisplayChar() == '.'){
                locationsList.add(locationExit);
            }
        }
        if (locationsList.size() > 0) {
            Random rand = new Random();
            int upperBound = locationsList.size();
            Location randomLocation = locationsList.get(rand.nextInt(upperBound));
            randomLocation.setGround(sprout);
        }
    }

    /**
     * Wither method for when Mature dies.
     * @param location Location instance where the Mature is to wither.
     */
    private void wither(Location location){
        Dirt dirt = new Dirt();
        location.setGround(dirt);
    }


    /**
     * Mature can experience the joys of time.
     * @param location Location instance of the Mature tree.
     */
    @Override
    public void tick(Location location) {
        int num = (int) (Math.random() * 100);
        super.tick(location);
        if (!this.hasCapability(Status.CONVERTED_TO_DIRT)){
            // 15% chance to spawn Koopa, if actor stands on it, cannot spawn Koopa
            if (!location.containsAnActor()) {
                if(num < KOOPA_SPAWN_RATE){
                    if (num < KOOPA_SPAWN_RATE*0.5){
                        this.spawnKoopa(location);
                    }
                    else{
                        this.spawnFlyingKoopa(location);
                    }
                }

            }

            this.increaseAge();
            // takes 5 turns to grow into small tree/sapling
            if (this.getAge() % TURNS_REQUIRED_TO_GROW == 0){
                this.growSprout(location);
            }

            // 20% chance to wither
            if (num < WITHER_CHANCE){
                this.wither(location);
            }
        }


    }

    /**
     * String method to return Mature as a string.
     * @return returns Mature as a string.
     */
    public String toString(){
        return "Mature";
    }
}
