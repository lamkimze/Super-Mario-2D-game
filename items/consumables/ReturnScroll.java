package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Special scroll that allows teleportation.
 * @author Clinton Nguyen
 * @version 1
 */
public class ReturnScroll extends ConsumableItem {
    private Location startingLocation;

    /***
     * Constructor.
     */
    public ReturnScroll() {
        super("Return Scroll", '~', false);
        addCapability(Status.PERMANENT);
    }

    /**
     * Method to save the starting location of the return scroll.
     * @param actor Actor using the return scroll.
     * @param map map of the game.
     */
    public void saveStartingLocation(Actor actor, GameMap map){
        startingLocation = map.locationOf(actor);
    }

    /**
     * Method to use the return scroll.
     * @param actor actor that consume the consumable
     * @param map the map the actor is on
     * @return returns a string of where the actor is teleporting to (or if they can't).
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        super.consume(actor, map);
        if (this.hasCapability(Status.ACTIVATED)){
            if (!map.isAnActorAt(startingLocation)){
                map.moveActor(actor, startingLocation);
                this.removeCapability(Status.ACTIVATED);
                return actor + " used the " + this + " to teleport to (" + startingLocation.x() + ", " + startingLocation.y() + ")";
            }
            else{
                return actor + "cannot teleport to (" + startingLocation.x() + ", " + startingLocation.y() + ")" + " because of an unexpected obstacle!";
            }

        }
        else{
            this.addCapability(Status.ACTIVATED);
            this.saveStartingLocation(actor, map);
            return actor + " used the " + this + " to set the initial location to (" + startingLocation.x() + ", " + startingLocation.y() + ")";
        }
    }
}
