package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.BuyAction;
import game.actions.CommunicationAction;
import game.actions.GiftAction;
import game.actors.actortypes.SpeakableActor;
import game.items.consumables.Bottle;
import game.items.consumables.PowerStar;
import game.items.consumables.SuperMushroom;
import game.items.weapons.Wrench;

/**
 * Class which contains methods and attributes for the friendly NPC Toad.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class Toad extends Ally implements SpeakableActor {

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 100);
        this.addCapability(Status.CAN_GIFT);
    }

    /**
     * Play the turn for the NPC actor Toad
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * A method where an actor speaks to a target actor/player.
     * @param actor the target which Toad is speaking too.
     * @return a String which is the random speech option
     */
    @Override
    public String speakTo(Actor actor) {

        this.speechLines.addOption("The Princess is depending on you! You are our only hope.");
        this.speechLines.addOption("Being imprisoned in these walls can drive a fungus crazy :(");
        if (!actor.hasCapability(Status.SHELL_BREAKER)){
            this.speechLines.addOption("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!actor.hasCapability(Status.INVINCIBLE)){
            this.speechLines.addOption("You better get back to finding the Power Stars.");
        }
        String speechLine = this.speechLines.getRandomOption();
        return "Toad: \"" + speechLine + "\"";
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList which contains all the Actions the otherActor can do the current Actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionsToToad = new ActionList();

        if (otherActor.hasCapability(Status.LISTENER)) {
            actionsToToad.add(new CommunicationAction(this));
            if (this.hasCapability(Status.CAN_GIFT)){
                actionsToToad.add(new GiftAction(this, new Bottle()));
            }

        }
        if (otherActor.hasCapability(Status.BUYER)) {
            actionsToToad.add(new BuyAction(new PowerStar()));
            actionsToToad.add(new BuyAction(new SuperMushroom()));
            actionsToToad.add(new BuyAction(new Wrench()));
        }
        return actionsToToad;
    }

}
