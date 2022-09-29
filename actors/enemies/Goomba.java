package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.actortypes.AttackingActor;
import game.Status;
import game.actors.actortypes.FollowingActor;
import game.actors.actortypes.WanderableActor;


/**
 * A little fungus guy.
 * @author Clinton Nguyen, 31500145
 * @version 1
 */
public class Goomba extends Enemy implements WanderableActor, FollowingActor {
	private static final int CHANCE_TO_SUICIDE = 10;
	private static final int GOOMBA_HIT_POINTS = 20;
	private static final int GOOMBA_INTRINSIC_DAMAGE = 10;
	private static final String GOOMBA_ATTACK_VERB = "kick";



	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', GOOMBA_HIT_POINTS, GOOMBA_INTRINSIC_DAMAGE, GOOMBA_ATTACK_VERB);
		this.registerAsWanderable(super.behaviours);
		this.registerAsFollower(super.behaviours);
	}

	/**
	 * Determines what a Goomba should do next.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return returns action that Goomba should do.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (hasCapability(Status.RESET)) {
			map.removeActor(this);
		} else {
			if (!this.suicide(map)) {
				return super.getActionFromBehaviour(map);
			}
		}
		return new DoNothingAction();
	}

	/**
	 * Suicide method that allows Goomba to die at a 10% chance every turn.
	 * @param map the map Goomba is being removed from.
	 * @return bool value of whether or not Goomba suicided or not.
	 */
	private boolean suicide(GameMap map) {
		int num = (int) (Math.random() * 100);
		if (num < CHANCE_TO_SUICIDE) {
			map.removeActor(this);
			return true;
		}
		return false;
	}


}
