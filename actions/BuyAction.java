package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.wallet.WalletManager;
import game.wallet.Wallet;
import game.items.BuyableItem;


/**
 * An action class which allows for the buying of items
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class BuyAction extends Action {
    /**
     * The item to buy
     */
    private BuyableItem item;

    /**
     * Constructor
     * @param item the item to buy
     */
    public BuyAction(BuyableItem item){
        this.item = item;
    }

    /**
     * Perform the Action, which is to buy the item if possible
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String which describes the action taken
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (WalletManager.getInstance().hasWallet(actor)){ // the actor does have a wallet
            Wallet wallet = WalletManager.getInstance().getWallet(actor);
            int price = item.getPrice();

            if (wallet.getCredit() >= price){
                wallet.reduceCredit(price);
                return item.buy(actor);
            }
            else{
                return "You don't have enough coins";
            }
        }
        return actor + " does not have a wallet"; // should not return from here unless error
    }

    /**
     * Returns a descriptive string.
     * @param actor The actor performing the action.
     * @return String which describes the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Mario buys " + this.item.toString() + " ($" + this.item.getPrice() + ")";
    }
}
