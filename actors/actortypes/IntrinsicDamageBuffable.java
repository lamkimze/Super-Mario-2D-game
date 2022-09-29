package game.actors.actortypes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.buff.IntrinsicDamageManager;

/**
 * Interface for providing Actors with the ability to get Intrinsic Damage Buffed
 * @author Rory-Tobin Underwood
 * @version 1
 */
public interface IntrinsicDamageBuffable {

    /**
     * Default method that register the actor to the IntrinsicDamageManager
     * @param actor Actor to be registered
     * @param currentIntrinsicDamage IntrinsicDamage of the Actor
     */
    default void registerAsIntrinsicDamageBuffable(Actor actor, int currentIntrinsicDamage){
        IntrinsicDamageManager.getInstance().registerActor(actor, currentIntrinsicDamage);
    }

    /**
     * Increases the intrinsic damage of the Actor
     * @param actor Actor to be buffed
     * @param increaseInIntrinsicDamage the increase in Intrinsic Damage
     */
    default void increaseIntrinsicDamage(Actor actor, int increaseInIntrinsicDamage){
        IntrinsicDamageManager.getInstance().increaseIntrinsicDamageForAnActor(actor, increaseInIntrinsicDamage);
    }

    /**
     * Creates a new IntrinsicWeapon for the Actor
     * @param actor the Actor the weapon is for
     * @param verb the verb of the weapon
     * @return a new IntrinsicWeapon
     */
    default IntrinsicWeapon createIntrinsicWeapon(Actor actor, String verb){
        return new IntrinsicWeapon(IntrinsicDamageManager.getInstance().getIntrinsicDamage(actor), verb);
    }
}
