package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.NonPlayableActor;
import game.actors.actortypes.AttackingActor;
import game.actors.actortypes.DrinkingActor;
import game.actors.actortypes.IntrinsicDamageBuffable;
import game.Status;
import game.reset.Resettable;

/**
 * A class that act as an enemy extended from an actor because enemy is an actor and implements resettable because enemy
 * can be reset
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public abstract class Enemy extends NonPlayableActor implements Resettable, DrinkingActor, IntrinsicDamageBuffable, AttackingActor {
    private final String attackVerb;

    /**
     * Constructor.
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param baseDamage the basic damage of enemy
     * @param attackVerb the verb describing the attack
     */
    public Enemy(String name, char displayChar, int hitPoints, int baseDamage, String attackVerb) {
        super(name, displayChar, hitPoints);
        this.attackVerb = attackVerb;
        registerInstance();
        this.addCapability(Status.FLOOR_RESTRICTED);
        registerAsDrinker(behaviours);
        this.addCapability(Status.DRINKER);
        registerAsIntrinsicDamageBuffable(this, baseDamage);
        registerAsAttacker(behaviours);
    }

    /**
     * Do some damage to the Enemy. Also changes Enemy status to 'AGGROED'.
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        this.addCapability(Status.AGGROED);
    }

    /**
     * Gets the allowableActions the Actor can do to this Enemy
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ArrayList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Method used to add status of reset.
     */
    @Override
    public void setToResetInstance() {
        addCapability(Status.RESET);
    }

    /**
     * General instance reseter for all enemy (override for more specific resetting effects)
     * @param location the location of the enemy
     */
    @Override
    public void resetTheInstance(Location location) {
        location.map().removeActor(this);
    }

    /**
     * Returns the IntrinsicWeapon for this enemy
     * @return an IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return this.createIntrinsicWeapon(this, attackVerb);
    }
}
