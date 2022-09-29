package game.fillablesystem;

/**
 * Class which contains methods for Filler objects.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public interface Filler {

    /**
     * Default method that fills a Fillable object
     * @param fillable Fillable object to be filled
     */
    default void fill(Fillable fillable){
        this.fillReduceCapacityAmount();
        fillable.fillUp(createNewFillableCharge());
    }

    /**
     * Abstract method that creates a new fillable charge
     * @return a FillableCharge
     */
    FillableCharge createNewFillableCharge();

    /**
     * Abstract method that will reduce the capacity caused by filling up
     */
    void fillReduceCapacityAmount();
}
