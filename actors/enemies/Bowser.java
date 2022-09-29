package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actors.actortypes.AttackingActor;
import game.actors.actortypes.FollowingActor;
import game.items.Key;

/**
 * a class indicates an enemy Bowser which act as a boss of this game
 * @author Lam Kim Ze, 31860346
 * @version 2.0
 */
public class Bowser extends Enemy implements FollowingActor {

    /**
     * class constant
     */
    private static final int HIT_POINT = 500;
    private static final int DAMAGE = 80;
    private static final String BOWSER_ATTACK_VERB = "punches";

    private Location startingLocation;

    /**
     * Constructor
     * @param startingLocation the initial location where the Bowser is spawned
     */
    public Bowser(Location startingLocation){
        super("Bowser", 'B', HIT_POINT, DAMAGE, BOWSER_ATTACK_VERB);
        this.addCapability(Status.FIRE_ATTACK);
        this.addCapability(Status.SPAWN_KEY);
        this.registerAsFollower(this.behaviours);
        this.addItemToInventory(new Key());
        this.startingLocation = startingLocation;
    }

    /**
     * adding reset capability to this enemy
     */
    public void setToResetInstance(){
        addCapability(Status.RESET);
    }

    /**
     * a method to tell Bowser what to do every turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the actions to be done or does nothing
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
     * reset the position and the setting of Bowser when it is being reset
     * @param location of bowser
     */
    public void resetTheInstance(Location location){
        this.heal(this.getMaxHp());
        this.removeCapability(Status.AGGROED);
        if(!(startingLocation.getActor() == this)){
            if (startingLocation.containsAnActor()){
                startingLocation.map().removeActor(startingLocation.getActor());
            }
            location.map().moveActor(this, startingLocation);
        }

        this.removeCapability(Status.RESET);
    }
}
