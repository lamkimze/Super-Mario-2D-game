package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.highgrounds.WarpPipe;

/**
 * An action class which allows for Teleport between WarpPipes
 * @author Lam Kim Ze, Rory Tobin-Underwood
 * @version 2
 */
public class TeleportAction extends MoveActorAction {
    /**
     * Destination WarpPipe
     */
    private WarpPipe destinationPipe;
    /**
     * Starting WarpPipe
     */
    private WarpPipe startingPipe;

    /**
     * Constructor
     * @param targetLocation the location to move to
     * @param destinationPipe the destination WarpPipe location
     * @param startingPipe the starting WarpPipe instance
     */
    public TeleportAction(Location targetLocation, WarpPipe destinationPipe, WarpPipe startingPipe) {
        super(targetLocation,"");
        this.destinationPipe = destinationPipe;
        this.startingPipe = startingPipe;
    }

    /**
     * Executes the Teleport Action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return descriptive string for the action taken
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.destinationPipe.setOutLocation(map.locationOf(actor));
        this.destinationPipe.setDestinationPipe(startingPipe);

        if (super.moveToLocation.map().isAnActorAt(super.moveToLocation)){
            super.moveToLocation.map().removeActor(super.moveToLocation.map().getActorAt(super.moveToLocation));
        }
        super.moveToLocation.map().moveActor(actor, super.moveToLocation);

        return this.menuDescription(actor);
    }

    /**
     * Returns a descriptive string for the menu
     * @param actor The actor performing the action.
     * @return a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleport to " + super.moveToLocation.map();
    }
}
