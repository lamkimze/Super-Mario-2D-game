package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that indicates the lava ground
 * @author Lam Kim Ze, 31860346
 * @version 1.0
 */
public class Lava extends Ground {

    /**
     * class constants
     */
    public static final int LAVA_DAMAGE = 15;

    /**
     * constructor
     */
    public Lava(){
        super('L');
    }


    /**
     * to give actor on the lava damage every turns
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            location.getActor().hurt(LAVA_DAMAGE);
        }

    }

    /**
     * to check whether the actor can enter the lava area or not
     * @param actor the Actor to check
     * @return true if the actor has lava_restricted or no_restricted status and false for others
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(Status.LAVA_RESTRICTED);
    }

    /**
     * the name of lava
     * @return lava
     */
    @Override
    public String toString() {
        return "Lava";
    }
}
