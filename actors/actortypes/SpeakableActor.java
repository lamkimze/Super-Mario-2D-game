package game.actors.actortypes;

import edu.monash.fit2099.engine.actors.Actor;
import game.speech.SpeechLines;

/**
 * Interface for all Actors who can speak
 * @author Rory Tobin-Underwood, 31452434
 * @version 1.0
 */
public interface SpeakableActor {

    /**
     * Instance of SpeechLines
     */
    SpeechLines speechLines = new SpeechLines();

    /**
     * Abstract method that all SpeakableActors will implement
     * @param actor the actor to speak too
     * @return a String speech line for the actor
     */
    public String speakTo(Actor actor);
}
