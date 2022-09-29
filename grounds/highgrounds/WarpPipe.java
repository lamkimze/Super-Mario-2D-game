package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpActorAction;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;

/**
 * a class that indicates the place where the player can teleport to another map
 * @author Lam Kim Ze, 31860346
 * @version 2.1
 */

public class WarpPipe extends HighGround{

    /**
     * class constants
     */
    private boolean SPAWN_BEFORE = false;
    private static final int WARPPIPE_JUMP_SUCCESS_RATE = 100;
    private Location outLocation;
    private WarpPipe destinationPipe;

    /**
     * constructor
     */
    public WarpPipe(){
        super('C');
        this.addCapability(Status.TELE_GUARD);
        super.jumpSuccessRate = WARPPIPE_JUMP_SUCCESS_RATE;
    }

    /**
     * constructor
     * @param outLocation the location where the player is coming out when teleporting
     * @param destinationPipe the warpPiper that the player is teleporting to
     */
    public WarpPipe(Location outLocation, WarpPipe destinationPipe){
        super('C');
        this.addCapability(Status.TELE_GUARD);
        super.jumpSuccessRate = WARPPIPE_JUMP_SUCCESS_RATE;
        this.outLocation = outLocation;
        this.destinationPipe = destinationPipe;
    }

    /**
     * setter to set the destination of the player teleporting
     * @param outLocation the location of the final destination player teleporting
     */
    public void setOutLocation(Location outLocation) {
        this.outLocation = outLocation;
    }

    /**
     * setter to set the destination WarpPipe
     * @param destinationPipe the destination WarpPipe
     */
    public void setDestinationPipe(WarpPipe destinationPipe) {
        this.destinationPipe = destinationPipe;
    }

    /**
     * to spawn the Piranha Plant on the WarpPipe once
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if(location.getGround() == this && !SPAWN_BEFORE){
            spawnPiranhaPlant(location);
            SPAWN_BEFORE = true;
        }
    }

    /**
     * spawning the Piranha Plant
     * @param location the location to spawn Piranha Plant
     */
    private void spawnPiranhaPlant(Location location){
        PiranhaPlant piranhaPlant = new PiranhaPlant();
        location.addActor(piranhaPlant);
    }

    /**
     * to check whether the actor can enter the WarpPipe or not
     * @param actor the Actor to check
     * @return return true if the actor has teleportable status and false if not
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.TELEPORTABLE)){
            return true;
        }
        return false;
    }

    /**
     * adding the action can be done by an actor on the WarpPipe by checking the status
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the actionlist that contain all the actions that can be done by actor on WarpPipe
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if(actor.hasCapability(Status.JUMPER) && !direction.equals("")){
            actionList.add(new JumpActorAction(location, direction, this));
        }
        else if(actor.hasCapability(Status.TELEPORTABLE)){
            actionList.add(new TeleportAction(this.outLocation, this.destinationPipe, this));
        }
        return actionList;
    }

    /**
     * give the name of WarpPipe
     * @return Warp Pipe
     */
    @Override
    public String toString() {
        return "Warp Pipe";
    }
}
