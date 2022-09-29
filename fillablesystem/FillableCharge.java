package game.fillablesystem;

import game.items.consumables.Consumable;

/**
 * An abstract class for FillableCharges
 * @author Rory Tobin-Underwood
 * @version 1
 */
public abstract class FillableCharge implements Consumable {
    /**
     * The name of the FillableCharge
     */
    protected String name;

    /**
     * Constructor
     * @param name the name of the FillableCharge
     */
    public FillableCharge(String name){
        this.name = name;
    }

    /**
     * Creates a String for the FillableCharge
     * @return String
     */
    @Override
    public String toString() {
        return this.name;
    }
}
