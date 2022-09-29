package game.actors.enemies.koopa;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.actions.DestroyShellAction;
import game.actors.actortypes.AttackingActor;
import game.actors.actortypes.FollowingActor;
import game.actors.actortypes.WanderableActor;
import game.actors.enemies.Enemy;

/**
 * an Koopa abstract class which indicates a Koopa type of enemy
 * @author Lam Kim Ze
 * @version 2.0
 */
public abstract class KoopaFleet extends Enemy implements WanderableActor, FollowingActor{

    /**
     * class constants
     */
    private static final int KOOPA_INTRINSIC_DAMAGE = 30;
    private static final String KOOPA_ATTACK_VERB = "punches";
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public KoopaFleet(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, KOOPA_INTRINSIC_DAMAGE, KOOPA_ATTACK_VERB);
        this.addCapability(Status.SHELL);
        this.registerAsWanderable(super.behaviours);
        this.registerAsFollower(super.behaviours);
    }

    /**
     * playTurn is a method used to tell Koopa what to do every turn by checking statuses.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns the action to be done OR does nothing depending on status of Koopa.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            this.resetTheInstance(map.locationOf(this));
        }
        else {
            this.isDormant();
            if (!this.hasCapability(Status.DORMANT)) {
                return this.getActionFromBehaviour(map);
            }
        }
        return new DoNothingAction();
    }

    /**
     * Method that indicates what actions Koopa is allowed to perform based on its status.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map current GameMap
     * @return returns list of actions that may be performed by otherActor and Koopa.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();

        // if DORMANT, Koopa will stay on ground.
        if(this.hasCapability(Status.DORMANT) & this.hasCapability(Status.SHELL) & otherActor.hasCapability(Status.SHELL_BREAKER)){
            actionList.add(new DestroyShellAction(this, direction));
            actionList.remove(new AttackAction(this, direction));
            return actionList;
        }
        else if (!this.hasCapability(Status.DORMANT)){
            return super.allowableActions(otherActor, direction, map);
        }
        return actionList;
    }

    /**
     * Method to check if Koopa should be deemed Dormant.
     */
    public void isDormant(){
        if (!this.isConscious()){
            this.addCapability(Status.DORMANT);
            setDisplayChar('D');
            // change display character to D
        }
    }
}
