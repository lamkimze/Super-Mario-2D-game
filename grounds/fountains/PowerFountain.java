package game.grounds.fountains;

import game.fillablesystem.FillableCharge;
import game.fillablesystem.PowerFillableCharge;

/**
 * Class for the PowerFountain Ground object.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class PowerFountain extends Fountain{
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A', "Power Fountain");
    }

    /**
     * Creates a PowerFillableCharge
     * @return a FillableCharge in the form of PowerFillableCharge
     */
    @Override
    public FillableCharge createNewFillableCharge() {
        return new PowerFillableCharge();
    }
}
