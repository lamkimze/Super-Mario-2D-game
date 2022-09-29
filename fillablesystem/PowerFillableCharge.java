package game.fillablesystem;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.buff.IntrinsicDamageManager;

/**
 * The class for the PowerFillableCharge
 * @author Rory Tobin-Underwood
 * @version 1
 */
public class PowerFillableCharge extends FillableCharge {
    /**
     * Constant which determines how much INTRINSIC_ATTACK is gained when consumed
     */
    private static final int POWER_WATER_INTRINSIC_ATTACK_BUFF = 15;

    /**
     * Constructor
     */
    public PowerFillableCharge() {
        super("Power water");
    }


    /**
     * Performs the consummation of the PowerFillableCharge
     * @param actor actor that consume the consumable
     * @param map the map the actor is on
     * @return a Descriptive string of the consummation
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        if (IntrinsicDamageManager.getInstance().isActorRegistered(actor)) {
            IntrinsicDamageManager.getInstance().increaseIntrinsicDamageForAnActor(actor, POWER_WATER_INTRINSIC_ATTACK_BUFF);
        }

        return actor + " consumed " + this;
    }
}
