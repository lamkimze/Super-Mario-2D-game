package game.buff;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.HashMap;

/**
 * Manager class for all Actors who implement the IntrinsicDamageBuffable interface
 * @author Rory-Tobin Underwood
 * @version 1
 */
public class IntrinsicDamageManager {

    /**
     * HashMap of all intrinsicDamages
     */
    private HashMap<Actor, Integer> allIntrinsicDamages = new HashMap<>();

    /**
     * A singleton IntrinsicDamageManager instance
     */
    private static IntrinsicDamageManager instance;

    /**
     * Get the singleton instance of IntrinsicDamageManager
     * @return IntrinsicDamageManager singleton instance
     */
    public static IntrinsicDamageManager getInstance(){
        if(instance == null){
            instance = new IntrinsicDamageManager();
        }
        return instance;
    }

    /**
     * Adds an actor and their intrinsic damage to the allIntrinsicDamages HashMap
     * @param actor the Actor to be added, the key for the HashMap
     * @param currentIntrinsicDamage the Intrinsic Damage to be added, the value for the HashMap
     */
    public void registerActor(Actor actor, int currentIntrinsicDamage){
        allIntrinsicDamages.put(actor, currentIntrinsicDamage);
    }

    /**
     * Increases the Intrinsic Damage for an actor
     * @param actor the Actor to be Buffed, the key for the HashMap
     * @param intrinsicDamageIncrease the amount of Intrinsic Damage to be buffed
     */
    public void increaseIntrinsicDamageForAnActor(Actor actor, int intrinsicDamageIncrease){
        int currentIntrinsicDamage = allIntrinsicDamages.get(actor);
        allIntrinsicDamages.put(actor, currentIntrinsicDamage + intrinsicDamageIncrease);
    }

    /**
     * Returns the Intrinsic Damage for an Actor
     * @param actor the key for the HashMap
     * @return an int which represents the Intrinsic Damage for the actor
     */
    public int getIntrinsicDamage(Actor actor){
        return allIntrinsicDamages.get(actor);
    }

    /**
     * Checks to see if the Actor is registered
     * @param actor the Actor to check
     * @return a boolean which is True if the Actor is registered, False otherwise.
     */
    public boolean isActorRegistered(Actor actor){
        return allIntrinsicDamages.get(actor) != null;
    }
}
