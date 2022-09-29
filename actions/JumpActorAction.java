package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.highgrounds.HighGround;

/**
 * An action class that enable actor to jump to the high ground
 * @author Lam Kim Ze, 31860346
 * @version 3.0
 */
public class JumpActorAction extends MoveActorAction{

    /**
     * creating high ground
     */
    private HighGround target;

    /**
     * constructor
     * @param moveToLocation the location of the jump
     * @param direction the direction of the jump
     * @param highGround the instance of HighGround the actor is jumping to
     */
    public JumpActorAction(Location moveToLocation, String direction, HighGround highGround){
        super(moveToLocation, direction);
        this.target = highGround;
    }

    /**
     * Executes the Jumping action, using Random to find out if successful jump or unsuccessful jump.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing that the actor success or fail to jump to the high ground
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        int temp = (int) Math.floor(Math.random() * 100);
        // if actor has Tall capability
        if (!(actor.hasCapability(Status.TALL))) {
            // if the moveToLocation is wall
            int suc = this.target.getJumpSuccessRate();
            if (this.target.hasCapability(Status.JUMPABLE)){
                if ( suc > temp ) {
                    map.moveActor(actor, moveToLocation);
                    output = "Yay! Mario successfully jumped to " + target.toString() + " (" + moveToLocation.x() + "," + moveToLocation.y() + ") ";
                } else {
                    actor.hurt(this.target.getJumpDamage());
                    output = "Ouch! Mario failed to jump and received damage " + target.getJumpDamage();
                }
            }
        }
        // if actor consume Super Mushroom

        else {
            map.moveActor(actor, moveToLocation);
            output = "Mario successfully jumped to " + target.toString() + " (" + moveToLocation.x() + "," + moveToLocation.y() + ") ";
        }

        return output;
    }

    /**
     * Returns a descriptive string for the actor attacking the target
     * @param actor The actor performing the action.
     * @return String a descriptive string
     */
    public String menuDescription(Actor actor){
        return actor + " jumps to " + direction + " " + moveToLocation.getGround() + " (" + moveToLocation.x() + "," + moveToLocation.y() + ") ";
    }

}
