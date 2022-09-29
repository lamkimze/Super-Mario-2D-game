package game.fillablesystem;

import java.util.Stack;

/**
 * An interface which allows for an object to be Fillable.
 * @author Rory Tobin-Underwood
 * @version 1
 */
public interface Fillable {
    /**
     * A stack of FillableCharges
     */
    Stack<FillableCharge> chargeStack = new Stack<>();

    /**
     * Adds a FillableCharge to the top of the stack
     * @param fillableCharge the FillableCharge to be added to the top of the stack
     */
    default void fillUp(FillableCharge fillableCharge){
        this.chargeStack.push(fillableCharge);
    }

    /**
     * Pops the FillableCharge from the top of the stack.
     * @return the FIllableCharge at the top of the stack.
     */
    default FillableCharge getTopFillableCharge(){
        return this.chargeStack.pop();
    }
}
