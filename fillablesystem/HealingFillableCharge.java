package game.fillablesystem;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * The class for the HealingFillableCharge
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class HealingFillableCharge extends FillableCharge {
    /**
     * Constant which determines how much HP is healed when consumed
     */
    private static final int HEALING_AMOUNT = 50;

    /**
     * Constructor
     */
    public HealingFillableCharge() {
        super("Healing Water");
    }

    /**
     * Performs the consummation of the HealingFillableCharge
     * @param actor actor that consume the consumable
     * @param map the map the actor is on
     * @return a Descriptive string of the consummation
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.heal(HEALING_AMOUNT);
        return actor + " consumed " + super.name;
    }
}
