package game.actors.player;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.actortypes.IntrinsicDamageBuffable;
import game.buff.fadingbuff.FadingBuffManager;
import game.Status;
import game.reset.ResetGameAction;
import game.reset.Resettable;
import game.quest.Quester;
import game.wallet.WalletManager;

/**
 * Class representing the Player.
 * @author Clinton Nguyen, Rory-Tobin Underwood, Lam Kim Ze
 * @version 1
 */
public class Player extends Actor implements Resettable, Quester, IntrinsicDamageBuffable {
	private static final int PLAYER_HIT_POINTS = 10000;
	private static final int PLAYER_BASE_DAMAGE = 5;
	private static final int MONEY_STARTING_VALUE = 0;


	private final Menu menu = new Menu();

	private static Player instance;

    /**
	 * A singleton player instance, made because there will only be one player and this allows for other classes to
     * access the Player.
	 * @return return the instance actor
     */
	public static Player getInstance(){
		if(instance == null){
			instance = new Player();
		}
		return instance;
	}

    /**
	 * Constructor.
     */
	private Player() {
		super("Mario", 'm', PLAYER_HIT_POINTS);
		WalletManager.getInstance().addWallet(this, MONEY_STARTING_VALUE);
		WalletManager.getInstance().addWallet(this, 1000);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_RESET);
		this.addCapability(Status.LISTENER);
		this.addCapability(Status.BUYER);
		this.addCapability(Status.JUMPER);
		this.addCapability(Status.LAVA_RESTRICTED);
		registerInstance();
		FadingBuffManager.getInstance().addActor(this);
		registerAsIntrinsicDamageBuffable(this, PLAYER_BASE_DAMAGE);
	}

	/**
	 * player get some damage
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		super.hurt(points);
		if (this.hasCapability(Status.TALL)){
			this.removeCapability(Status.TALL);
		}
	}

	/**
	 * Select and return an action to perform on the current turn and check the buff on player.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return action that the player choose to execute
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if(!(this.isConscious())){
			map.removeActor(this);
		}
		// return/print the console menu
		System.out.println("Mario " + this.printHp() + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");
		System.out.println("Wallet balance: $" + WalletManager.getInstance().getWallet(this).getCredit());
		if (this.hasCapability(Status.CAN_RESET)){
			actions.add(new ResetGameAction());
		}
		FadingBuffManager.getInstance().tickFadingBuffs(this);

		this.questManager.displayQuestsToConsole(map);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		return menu.showMenu(this, actions, display);
	}

	/**
	 * showing the character in the map to indicate the current status of player
	 * @return a character represent the player
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * reset the status on the player
	 */
	@Override
	public void setToResetInstance() {
		this.resetTheInstance(null);
	}

	/**
	 * Actually resets the Player instance
	 * @param location null for Player but other actors use it
	 */
	@Override
	public void resetTheInstance(Location location) {
		int maxHp = this.getMaxHp();
		heal(maxHp); //will round to max hp

		if (this.hasCapability(Status.INVINCIBLE)){
			FadingBuffManager.getInstance().removeBuff(this, Status.INVINCIBLE);
			this.removeCapability(Status.INVINCIBLE);
		}
		if (this.hasCapability(Status.TALL)){
			this.removeCapability(Status.TALL);
		}
	}

	/**
	 * Calls the createIntrinsicWeapon in the IntrinsicDamageBuffable interface
	 * @return a new IntrinsicWeapon for the Player
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return this.createIntrinsicWeapon(this, "punches");
	}

}
