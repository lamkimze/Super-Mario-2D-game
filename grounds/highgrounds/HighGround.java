package game.grounds.highgrounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.InvincibleMoveActorAction;
import game.actions.JumpActorAction;

/**
 * high ground is a class extended from ground class which represent a higher ground
 * @author Lam Kim Ze, 31860346
 * @version 3.0
 */

public abstract class HighGround extends Ground {
    /**
     * constructor
     * @param type_ground a character represent a type of high ground
     */
    public HighGround(char type_ground){
        super(type_ground);
        addCapability(Status.JUMPABLE);
    }

    /**
     * initialise the jump success rate for high ground
     */
    protected int jumpSuccessRate;

    /**
     * initialise the jump damage for the high ground
     */
    protected int jumpDamage;

    /**
     * getter for success jumping rate
     * @return the success rate of jumping onto the high ground
     */
    public int getJumpSuccessRate() {
        return jumpSuccessRate;
    }

    /**
     * getter for damage due to a fail jump
     * @return damage received by actor due to unsuccessful jump
     */
    public int getJumpDamage() {
        return jumpDamage;
    }

    /**
     * adding the invincible move for player if he has invincible status and meet high ground and adding jump action for
     * player if he meets high ground without invincible status
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the action list which actor can use
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        // if actor has jumping capability
        if(actor.hasCapability(Status.JUMPER)){
            if (!location.canActorEnter(actor) && !direction.equals("")){
                // if actor consumed power start
                if(actor.hasCapability(Status.INVINCIBLE)){
                    // actor gets InvincibleMoveActorAction
                    actionList.add(new InvincibleMoveActorAction(location, direction));
                }
                else{
                    // actor is allowed to jump onto the high ground
                    actionList.add(new JumpActorAction(location, direction, this));
                }
            }
        }

        return actionList;
    }
}
