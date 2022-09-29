package game.grounds.fountains;

/**
 * Class for the HealthFountain Ground object.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
import game.fillablesystem.FillableCharge;
import game.fillablesystem.HealingFillableCharge;

/**
 * a class indicates a health fountain
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class HealthFountain extends Fountain{
    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H', "Health Fountain");
    }

    /**
     * Creates a HealingFillableCharge
     * @return a FillableCharge in the form of a HealingFillableCharge
     */
    @Override
    public FillableCharge createNewFillableCharge() {
        return new HealingFillableCharge();
    }
}
