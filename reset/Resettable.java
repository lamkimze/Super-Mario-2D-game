package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * an interface class which indicates resettable
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * HINT: play around with capability, the actual implementation happens in the tick or playTurn method.
     */
    void setToResetInstance();

    /**
     * Method that actually resets the instance
     * @param location the location of the item that is going to be reset
     */
    void resetTheInstance(Location location);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
