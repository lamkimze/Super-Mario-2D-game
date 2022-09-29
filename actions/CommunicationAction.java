package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.actortypes.SpeakableActor;

/**
 * An action class which allows for SpeakableActors to communication with other actors.
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public class CommunicationAction extends Action {

    /**
     * The SpeakableActor which will communication
     */
    private SpeakableActor speaker;

    /**
     * Constructor for the CommunicationAction
     * @param speaker the SpeakableActor which will communication
     */
    public CommunicationAction(SpeakableActor speaker){
        this.speaker = speaker;
    }

    /**
     * Perform the Action, which is to speak to the target Actor.
     * @param target actor who will be spoken too
     * @param map The map the actor is on.
     * @return returns the string to be said by speaker to target.
     */
    @Override
    public String execute(Actor target, GameMap map) {
        return speaker.speakTo(target);

    }

    /**
     * Returns a descriptive string.
     * @param actor The actor performing the action.
     * @return String which describes the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + this.speaker;
    }
}
