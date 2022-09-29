package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * Class representing the PiranhaPlant enemy
 * @author Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public class PiranhaPlant extends Enemy {
    private static final int HIT_POINT = 150;
    private static final int HIT_RATE = 50;
    private static final int DAMAGE = 90;
    private static final int RESET_INCREASE_IN_HIT_POINTS = 50;
    private static final String PIRANHA_PLANT_ATTACK_VERB = "chomps";


    /**
     * Constructor
     */
    public PiranhaPlant(){
        super("PiranhaPlant", 'Y', HIT_POINT, DAMAGE, PIRANHA_PLANT_ATTACK_VERB);
        this.addCapability(Status.TELE_GUARD);
    }

    /**
     * Plays the turn for the PiranhaPlant
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action for the PiranhaPlant to take
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(hasCapability(Status.RESET)){
            this.resetTheInstance(map.locationOf(this));
        }
        else{
            return this.getActionFromBehaviour(map);
        }
        return new DoNothingAction();
    }

    /**
     * Resets the PiranhaPlant instance
     * @param location location of the PiranhaPlant
     */
    public void resetTheInstance(Location location){
        this.heal(this.getMaxHp());
        this.increaseMaxHp(RESET_INCREASE_IN_HIT_POINTS);
        this.removeCapability(Status.RESET);
    }
}
