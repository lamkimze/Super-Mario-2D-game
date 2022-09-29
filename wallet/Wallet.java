package game.wallet;

/**
 * a wallet class which contains the credit
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class Wallet {

    /**
     * storing the credits money that an actor has
     */
    private int credit;

    /**
     * constructor
     * @param credit the initial credit for the player
     */
    public Wallet(int credit){
        this.credit = credit;
    }

    /**
     * getter to give the credit remaining for the player
     * @return integer of the credit remaining for the player
     */
    public int getCredit(){
        return this.credit;
    }

    /**
     * increasing the credit balance for the player
     * @param credit credit that need to increase
     */
    public void increaseCredit(int credit){
        this.credit += credit;
    }

    /**
     * reducing the credit balance for the player
     * @param credit credit that need to be deducted
     */
    public void reduceCredit(int credit){
        this.credit -= credit;
    }

}
