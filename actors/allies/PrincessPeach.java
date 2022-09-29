package game.actors.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.CommunicationAction;
import game.actions.EndGameAction;
import game.actors.actortypes.SpeakableActor;


/**
 * a class which indicate an ally called Princess Peach
 * @author Lam Kim Ze
 * @version 2.0
 */

public class PrincessPeach extends Ally implements SpeakableActor {
    /**
     * class constant
     */
    public static int PRINCESS_PEACH_HIT_POINTS = 50;

    /**
     * Constructor.
     */

    public PrincessPeach() {
        super("Princess Peach", 'P', PRINCESS_PEACH_HIT_POINTS);
        this.addCapability(Status.HANDCUFFED);
    }

    /**
     * a method to tell Princess Peach what to do every turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action to be done or does nothing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * a dialogue of Pincess Peach to the player
     * @param actor the actor to speak too
     * @return the dialog of Princess Peach
     */
    @Override
    public String speakTo(Actor actor) {
        if (!actor.hasCapability(Status.END_KEY_HOLDER)){
            this.speechLines.addOption("Bowser holds the key to rescuing me! You must defeat him first!");
            this.speechLines.addOption("Bowser has held me captive for so long now! Please help!");
        }
        String speechLine = this.speechLines.getRandomOption();
        return "Peach: \"" + speechLine + "\"";
    }

    /**
     * a series of action that can be done by Player on Princess Peach
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an actionlist that a player can be done on Princess Peach
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionsToPeach = new ActionList();
        if (otherActor.hasCapability(Status.END_KEY_HOLDER)){
            actionsToPeach.add(new EndGameAction());
        }
        else if (otherActor.hasCapability(Status.LISTENER)) {
            actionsToPeach.add(new CommunicationAction(this));
        }

        return actionsToPeach;
    }

}
