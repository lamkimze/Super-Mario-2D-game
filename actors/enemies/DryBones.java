package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.DestroyShellAction;
import game.actors.actortypes.FollowingActor;
import game.actors.actortypes.PatrolingActor;
import game.actors.actortypes.WanderableActor;

import java.util.ArrayList;

/**
 * DryBones Enemy class.
 * @author Clinton Nguyen
 * @version 1
 */
public class DryBones extends Enemy implements WanderableActor, FollowingActor, PatrolingActor {
    private static final int DRYBONES_HIT_POINTS = 75;
    private static final int DRYBONES_INTRINSIC_DAMAGE = 30;
    private static final String DRYBONES_ATTACK_VERB = "punches";
    private static final int TURNS_TO_REVIVE = 3;
    private int dryBoneSlumberTime = 0;


    /**
     * Constructor.
     * (We were using special box characters but the JavaDocs would not generate with them so we had to change it)
     */
    public DryBones() {
        super("DryBones", 'N', DRYBONES_HIT_POINTS, DRYBONES_INTRINSIC_DAMAGE, DRYBONES_ATTACK_VERB);
        addCapability(Status.SHELL);
        this.registerAsWanderable(super.behaviours);
        this.registerAsFollower(super.behaviours);
        this.registerAsAttacker(super.behaviours);
    }

    /**
     * Constructor with patrol coordinates.
     * @param patrolCoordinates the location that drtbone is going to be patrolling
     */
    public DryBones(ArrayList<Location> patrolCoordinates) {
        super("DryBones", 'N', DRYBONES_HIT_POINTS, DRYBONES_INTRINSIC_DAMAGE, DRYBONES_ATTACK_VERB);
        addCapability(Status.SHELL);
        this.registerAsWanderable(super.behaviours);
        this.registerAsFollower(super.behaviours);
        this.registerAsPatroller(super.behaviours, patrolCoordinates);
    }


    /**
     * Determines what a DryBones should do next.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns action that DryBones should do.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            this.resetTheInstance(map);
        }
        else {
            this.isRevivable();
            if (!this.hasCapability(Status.REVIVABLE)) {
                return this.getActionFromBehaviour(map);
            }
            else{
                if (dryBoneSlumberTime == TURNS_TO_REVIVE){
                    this.reviveDryBones();
                    setDisplayChar('N');
                    this.removeCapability(Status.REVIVABLE);
                }
                this.dryBoneAgeInSlumber();
            }
        }
        return new DoNothingAction();
    }

    /**
     * Method that indicates what actions DryBones is allowed to perform based on its status.
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return returns list of actions that may be performed by otherActor and DryBones.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();

        // if REVIVABLE, Dry Bones will stay on ground, then will revive after 3 turns. Can be killed similarly to Koopa.
        if(this.hasCapability(Status.REVIVABLE) & this.hasCapability(Status.SHELL) & otherActor.hasCapability(Status.SHELL_BREAKER)){
            actionList.add(new DestroyShellAction(this, direction));
            return actionList;
        }
        else if (!this.hasCapability(Status.REVIVABLE)){
            return super.allowableActions(otherActor, direction, map);
        }

        return actionList;
    }

    /**
     * Method to check if DryBones 'dies'. If so, he gains the 'revivable' status.
     */
    public void isRevivable(){
        if (!this.isConscious()){
            this.addCapability(Status.REVIVABLE);
            setDisplayChar('R');
            // change display character to R
        }
    }

    /**
     * Revives DryBones method.
     */
    private void reviveDryBones() {
        heal(this.getMaxHp());
    }


    /**
     * Adds time to how long DryBones is dead for.
     */
    public void dryBoneAgeInSlumber(){
        dryBoneSlumberTime += 1;
    }

    /**
     * Resets instance of DryBones.
     * @param map map of the game.
     */
    public void resetTheInstance(GameMap map){
        this.hurt(getMaxHp());
        this.removeCapability(Status.RESET);
    }

}
