package game.wallet;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.HashMap;

/**
 * A Singleton manager class which will contain all the Wallets in the system and using the instance of an Actor for the
 * key. This allows for more actors to easily be added to the wallet system.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class WalletManager {
    /**
     * A HashMap of Wallets accessed using the Actor as a key
     */
    private HashMap<Actor, Wallet> wallets = new HashMap<>();

    /**
     * A singleton wallet manager instance
     */
    private static WalletManager instance;

    /**
     * Get the singleton instance of wallet manager
     * @return WalletManager singleton instance
     */
    public static WalletManager getInstance(){
        if(instance == null){
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Adds a new Wallet to the HashMap at key Actor
     * @param actor the key where the new Wallet will be stored
     * @param startingValue the starting value for the new Wallet
     */
    public void addWallet(Actor actor, int startingValue){
        wallets.put(actor, new Wallet(startingValue));
    }

    /**
     * Gets a Wallet instance from the HashMap
     * @param actor the key for the Wallet to access
     * @return the wallet from the HashMap
     */
    public Wallet getWallet(Actor actor){
        return wallets.get(actor);
    }

    /**
     * Checks to see if an actor has an assigned wallet
     * @param actor the key for the Wallet to access
     * @return True if actor has wallet, false if not
     */
    public boolean hasWallet(Actor actor) {
        if (wallets.get(actor) != null){
            return true;
        }
        else{
            return false;
        }

    }
}
