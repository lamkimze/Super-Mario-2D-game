package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.wallet.WalletManager;
import game.items.Coin;

/**
 * An action class which indicates picking up the coin extended from pick up action
 * @author Clinton Nguyen, 31500145
 * @version 1.0
 */

public class PickUpCoinAction extends PickUpItemAction {

    /**
     * creating an instance of coin
     */
    private Coin coin;

    /**
     * Constructor.
     * @param coin coin that is being pick
     *
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    /**
     * Preforms the PickUpCoinAction, increases the actor's wallets credit and removes the coin from the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description on the pick up action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (WalletManager.getInstance().hasWallet(actor)){
            map.locationOf(actor).removeItem(this.coin);
            WalletManager.getInstance().getWallet(actor).increaseCredit(this.coin.getValue());
            return menuDescription(actor);
        }
        else{
            return actor + " does not have a wallet";
        }
    }

    /**
     * Returns a descriptive string for the actor attacking the target
     * @param actor The actor performing the action.
     * @return String a descriptive string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " picks up the " + this.coin;
    }
}
